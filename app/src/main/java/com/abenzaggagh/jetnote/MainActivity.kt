package com.abenzaggagh.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abenzaggagh.jetnote.data.NoteDataSource
import com.abenzaggagh.jetnote.model.Note
import com.abenzaggagh.jetnote.screens.NoteScreen
import com.abenzaggagh.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {

                val notes = remember {
                    mutableStateListOf<Note>()
                }

                NoteScreen(
                    notes = notes,
                    onAddNote = {
                        notes.add(it)
                    },
                    onRemoveNote = {
                        notes.remove(it)
                    }
                )
            }
        }
    }
}
