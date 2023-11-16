package com.example.appnotas.States

import com.example.appnotas.Data.Notas

data class NotasState(
    val listaNotas: List<Notas> = emptyList()
)

