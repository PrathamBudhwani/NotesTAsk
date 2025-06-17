package com.prathambudhwani.notestask.ui.theme.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prathambudhwani.notestask.ui.theme.data.Note
import com.prathambudhwani.notestask.ui.theme.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val dao=NoteDatabase.getDatabase(application).noteDao()
    val notes=dao.getAllNotes().asLiveData()

    fun addNote(text:String){
        viewModelScope.launch {
            dao.insert(Note(text=text))
        }
    }
    fun addNoteObject(note:Note){
        viewModelScope.launch {
            dao.insert(note)
        }
    }
    fun updateNote(note:Note){
        viewModelScope.launch {
            dao.update(note)
        }
    }
    fun deleteNote(note:Note){
        viewModelScope.launch {
            dao.delete(note)
        }
    }
}