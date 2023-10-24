package com.example.appnotas.Componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotasCard() {
    // Simulación de una lista de notas
    val notas = listOf(
        "Nota 1: Esta es la primera nota.",
        "Nota 2: Aquí tienes otra nota.",
        "Nota 3: Una tercera nota para mostrar."
        // Agrega más notas según sea necesario
    )

    // Estado para rastrear la selección de una nota
    var selectedNota by remember { mutableStateOf<String?>(null) }

    LazyColumn {
        items(notas) { nota ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        selectedNota = nota
                    },
                shape = MaterialTheme.shapes.medium // Forma del Card
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = nota,
                        style = MaterialTheme.typography.labelLarge // Estilo de texto
                    )
                }
            }
        }
    }
}




