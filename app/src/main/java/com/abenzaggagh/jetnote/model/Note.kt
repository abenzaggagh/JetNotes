package com.abenzaggagh.jetnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "Notes")
data class Note(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    var title: String,

    @ColumnInfo(name = "note_description")
    var description: String,

    @ColumnInfo(name = "note_entry_date")
    var entryDate: Date = Date.from(Instant.now())

)
