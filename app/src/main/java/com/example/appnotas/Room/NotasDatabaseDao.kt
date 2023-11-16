package com.example.appnotas.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appnotas.Data.Notas
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDatabaseDao {
    @Query("Select * From notas")
    fun obtenerNotas(): Flow<List<Notas>>
    @Query("Select * From notas where id= id")
    fun obtenerNota(id : Int): Flow<Notas>

    @Insert
    suspend fun agregarNotas(nota: Notas)

    @Update
    suspend fun actualizarNotas(nota: Notas)

    @Delete
    suspend fun borrarNotas(nota: Notas)


}