package com.prathambudhwani.notestask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prathambudhwani.notestask.ui.theme.ui.NoteScreen
import com.prathambudhwani.notestask.ui.theme.ui.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           val viewModel:NoteViewModel= viewModel()
            NoteScreen(viewModel)
        }
    }
}

