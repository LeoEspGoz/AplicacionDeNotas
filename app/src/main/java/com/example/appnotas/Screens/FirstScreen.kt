package com.example.appnotas.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appnotas.Navegation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController){
    Scaffold {
     BodyContent(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavController) {
    // Define una lista para almacenar las notas
    val notes = remember { mutableStateListOf<String>() }
    val noteText = remember { mutableStateOf("") }
    var isTextFieldVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {

        if (isTextFieldVisible) {
            // Campo de entrada de texto para la nota
            TextField(
                value = noteText.value,
                onValueChange = { noteText.value = it },
                label = { Text("Escribe una nota") }
            )
        }

        Row {
            // Botón para agregar notas y activar el campo de texto
            Button(
                onClick = {
                    isTextFieldVisible = true
                }
            ) {
                Text("Agregar Nota")
            }

            // Botón para guardar la nota en la lista y desactivar el campo de texto
            if (isTextFieldVisible) {
                Button(
                    onClick = {
                        notes.add(noteText.value)
                        noteText.value = ""
                        isTextFieldVisible = false
                    }
                ) {
                    Text("Guardar Nota")
                }
            }
        }
    }

    // Botón para navegar a otra pantalla (por ejemplo, la pantalla de SecondScreen)
    Button(
        onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route)
        }
    ) {
        Text("Recordatorios")
    }
}
