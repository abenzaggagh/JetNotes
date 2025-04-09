package com.abenzaggagh.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abenzaggagh.jetnote.model.Note
import com.abenzaggagh.jetnote.util.DateConverter

@Database(entities = [ Note::class ], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDatabaseDao

}