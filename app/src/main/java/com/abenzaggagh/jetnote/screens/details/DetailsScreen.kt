package com.abenzaggagh.jetnote.screens.details

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abenzaggagh.jetnote.components.NoteTopBar
import com.abenzaggagh.jetnote.navigation.NoteScreens
import com.abenzaggagh.jetnote.screens.home.NoteViewModel


@Composable
fun DetailsScreen(navController: NavController = rememberNavController(),
                  noteId: String? = "",
                  noteViewModel: NoteViewModel) {

    var note = noteViewModel.note.collectAsState().value

    LaunchedEffect(noteId) {
        noteViewModel.getNote(noteId.toString())
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
                    Column(modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                    ) {
                        Text(note.title, fontSize = 32.sp, fontWeight = Bold)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(note.description, fontSize = 18.sp)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            navController.navigate(route = NoteScreens.EditScreen.name + "/${note.id}")
                        }) {
                            Text("Edit")
                        }
                        Button(onClick = {
                            noteViewModel.removeNote(note)

                            navController.popBackStack()
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
