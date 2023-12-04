package com.example.appnotas.ViewModels

import com.example.appnotas.data.Note

sealed interface NotesEvents{
    object SortNotes: NotesEvents

    data class DeleteNote(val note: Note): NotesEvents

    data class SaveNotes(
        val title: String,
        val Description: String
    ): NotesEvents

}
