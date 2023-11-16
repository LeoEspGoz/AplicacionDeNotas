package com.example.appnotas.Room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appnotas.Data.Notas

@Database(
    entities = [Notas::class],
    version = 1,
    exportSchema = false

)
abstract class NotasDatabase : RoomDatabase() {
    abstract fun notasDao(): NotasDatabaseDao
}