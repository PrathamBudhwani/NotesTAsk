package com.prathambudhwani.notestask.ui.theme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prathambudhwani.notestask.ui.theme.data.Note
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var noteText by remember { mutableStateOf("") }
    var editingNote by remember { mutableStateOf<Note?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = noteText,
                onValueChange = { noteText = it },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(Modifier.padding(vertical = 8.dp)) {
                Button(onClick = {
                    if (editingNote != null) {
                        viewModel.updateNote(editingNote!!.copy(text = noteText))
                        editingNote = null
                    } else {
                        viewModel.addNote(noteText)
                    }
                    noteText = ""
                }) {
                    Text(if (editingNote != null) "Update" else "Add")
                }

                if (editingNote != null) {
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(onClick = {
                        editingNote = null
                        noteText = ""
                    }) {
                        Text("Cancel")
                    }
                }
            }

            val filteredNotes = notes.filter {
                it.text.contains(searchQuery, ignoreCase = true)
            }

            LazyColumn {
                items(filteredNotes) { note ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(note.text)
                            Text(
                                DateFormat.getDateTimeInstance().format(Date(note.timestamp)),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                noteText = note.text
                                editingNote = note
                            }) {
                                Text("Edit")
                            }
                            TextButton(onClick = {
                                noteToDelete = note
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }

            if (noteToDelete != null) {
                AlertDialog(
                    onDismissRequest = { noteToDelete = null },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Are you sure you want to delete this note?") },
                    confirmButton = {
                        TextButton(onClick = {
                            val deletedNote = noteToDelete!!
                            viewModel.deleteNote(deletedNote)
                            noteToDelete = null

                            coroutineScope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.addNoteObject(deletedNote)
                                }
                            }
                        }) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { noteToDelete = null }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}