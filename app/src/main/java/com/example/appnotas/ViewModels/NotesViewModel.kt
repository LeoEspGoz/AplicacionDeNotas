package com.example.appnotas.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnotas.data.Note
import com.example.appnotas.data.NotesDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NotesDao
): ViewModel() {
    private val isSortedByDateAdded= MutableStateFlow(true)

    private var notes =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort){
                dao.getNotesorderdByDateAdded()
            }else{
                dao.getNotesorderdByTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val _state = MutableStateFlow(NoteState())
    val state=
        combine(_state, isSortedByDateAdded, notes){ state, isSortedByDate, notes->
            state.copy(
                notes= notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())



    fun onEvent(event: NotesEvents){
        when(event){
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
                }
            is NotesEvents.SaveNotes -> {
                val note = Note(

                        title = state.value.title.value,
                        description = state.value.description.value,
                        dateAdded = System.currentTimeMillis()
                    )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }

            }
            NotesEvents.SortNotes -> {
                isSortedByDateAdded.value=!isSortedByDateAdded.value
            }
        }
    }
}