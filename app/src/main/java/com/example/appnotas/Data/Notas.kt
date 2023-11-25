package com.example.appnotas.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "notas")
data class Notas(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo("titulo")
    val titulo : String,
    @ColumnInfo("descripcion")
    val descripcion : String
)
