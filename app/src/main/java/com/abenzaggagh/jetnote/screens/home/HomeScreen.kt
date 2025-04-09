package com.abenzaggagh.jetnote.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abenzaggagh.jetnote.components.NoteButton
import com.abenzaggagh.jetnote.components.NoteInputText
import com.abenzaggagh.jetnote.components.NoteRow
import com.abenzaggagh.jetnote.components.NoteTopBar
import com.abenzaggagh.jetnote.model.Note
import com.abenzaggagh.jetnote.navigation.NoteScreens


@Composable
fun HomeScreen(navController: NavController = rememberNavController(),
               noteViewModel: NoteViewModel) {

    val notes = noteViewModel.noteList.collectAsState().value

    NoteView(
        notes = notes,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onNoteClick = {
            navController.navigate(route = NoteScreens.DetailsScreen.name + "/${it.id}")
        }
    )

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NoteView(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onNoteClick: (Note) -> Unit) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = { NoteTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Note",
                maxLines = 1,
                onTextChange = { input ->
                    if (input.all { char -> char.isLetter() || char.isWhitespace() }) title = input
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            NoteInputText(
                text = description,
                label = "Add a note",
                minLines = 3,
                maxLines = 3,
                onTextChange = { input ->
                    if (input.all { char -> char.isLetter() || char.isWhitespace() }) description = input
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(title = title, description = description)
                        )

                        title = ""
                        description = ""

                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(8.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))

            LazyColumn {
                items(items = notes, key = { it.id }) { note ->
                    NoteRow(
                        note = note,
                        onNoteClicked = {
                            onNoteClick(it)
                        }
                    )
                }
            }


        }
    }
}