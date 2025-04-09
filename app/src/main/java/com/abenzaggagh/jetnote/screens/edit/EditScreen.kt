package com.abenzaggagh.jetnote.screens.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abenzaggagh.jetnote.components.NoteInputText
import com.abenzaggagh.jetnote.components.NoteTopBar
import com.abenzaggagh.jetnote.screens.home.NoteViewModel

@Composable
fun EditScreen(navController: NavController = rememberNavController(),
               noteId: String? = "",
               noteViewModel: NoteViewModel) {

    LaunchedEffect(noteId) {
        noteViewModel.getNote(noteId.toString())
    }

    var note = noteViewModel.note.collectAsState().value

    var title by remember {
        mutableStateOf(note?.title ?: "")
    }

    var description by remember {
        mutableStateOf(note?.description ?: "")
    }

    Scaffold(
        topBar = { NoteTopBar(
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                    )
                }
            }
        ) }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (note != null) {

                Column(modifier = Modifier.padding(12.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.weight(1f)) {

                        NoteInputText(
                            text = title,
                            label = "Title",
                            maxLines = 1,
                            onTextChange = { input ->
                                if (input.all { char -> char.isLetter() || char.isWhitespace() }) title = input
                            },
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        NoteInputText(
                            text = description,
                            label = "Note",
                            minLines = 5,
                            maxLines = 8,
                            onTextChange = { input ->
                                if (input.all { char -> char.isLetter() || char.isWhitespace() }) description = input
                            },
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { navController.popBackStack() }) {
                            Text("Cancel")
                        }
                        Button(onClick = {
                            note.title = title
                            note.description = description

                            noteViewModel.updateNote(note)

                            navController.popBackStack()
                        }) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}
