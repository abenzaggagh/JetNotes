package com.abenzaggagh.jetnote.repository

import com.abenzaggagh.jetnote.data.NoteDatabaseDao
import com.abenzaggagh.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)

    suspend fun getNote(id: String) = noteDatabaseDao.getNoteById(id)

    fun getAllNote(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)

    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)

    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

}