package com.abenzaggagh.jetnote.data

import com.abenzaggagh.jetnote.model.Note

class NoteDataSource {

    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Android Compose", description = "Working on Android JetPack Compose Framework and Learning how to implement interfaces using Compose bjdb fjbdjv bdfj vbjde bvfjkdb vjkbnvjsb dvjbdjb djhvbjd kbvjkbvd jbvjd bvjbdjvb")
        )
    }

}