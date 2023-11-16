package com.example.appnotas.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "notas")
data class Notas(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo("Titulo")
    val Titulo : String,
    @ColumnInfo("Descripcion")
    val Descripcion : String
)
