package com.abenzaggagh.jetnote.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.abenzaggagh.jetnote.data.NoteDataSource
import com.abenzaggagh.jetnote.model.Note

class NoteViewModel: ViewModel() {

    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) = noteList.add(note)

    fun removeNote(note: Note) = noteList.remove(note)

    fun getAllNotes(): List<Note> = noteList

}