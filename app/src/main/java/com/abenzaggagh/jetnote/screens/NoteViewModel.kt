package com.abenzaggagh.jetnote.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abenzaggagh.jetnote.data.NoteDataSource
import com.abenzaggagh.jetnote.model.Note
import com.abenzaggagh.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())

    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNote().distinctUntilChanged().collect { notes ->
                if (notes.isEmpty()) {
                    Log.d("Note", "Notes List Empty")
                    _noteList.value = emptyList<Note>()
                } else {
                    _noteList.value = notes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }


    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun removeAll() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

}