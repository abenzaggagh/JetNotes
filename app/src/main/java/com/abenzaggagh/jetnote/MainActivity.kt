package com.abenzaggagh.jetnote


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.abenzaggagh.jetnote.navigation.NoteNavigation
import com.abenzaggagh.jetnote.screens.home.NoteViewModel
import com.abenzaggagh.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                val noteViewModel: NoteViewModel by viewModels()

                NoteNavigation(noteViewModel)
            }
        }
    }
}


