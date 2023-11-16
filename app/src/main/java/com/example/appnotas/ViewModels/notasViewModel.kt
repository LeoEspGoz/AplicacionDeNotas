package com.example.appnotas.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnotas.Data.Notas
import com.example.appnotas.Room.NotasDatabaseDao
import com.example.appnotas.States.NotasState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class notasViewModel(
    private val dao: NotasDatabaseDao
): ViewModel() {
    var state by mutableStateOf(NotasState())
        private set
    init{
        viewModelScope.launch {
            dao.obtenerNotas().collectLatest {
                state= state.copy(
                    listaNotas = it
                )
            }
        }
    }

    fun agregarNota(nota: Notas)= viewModelScope.launch {
        dao.agregarNotas(nota=nota)
    }
    fun actualizarNota(nota: Notas)= viewModelScope.launch {
        dao.actualizarNotas(nota=nota)
    }
    fun eliminarNota(nota: Notas)= viewModelScope.launch {
        dao.borrarNotas(nota=nota)
    }
}