package com.example.appnotas.Componentes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.ui.tooling.preview.Preview
import com.example.appnotas.ui.theme.AppNotasTheme

@Composable
fun NotasCard() {

    val notas = listOf(
        "Notas 1: Esta es una notas.",
        "Notas 2: Comprar leche.",
        "Notas 1: Tarea de movil para mañana."
    )
    LazyColumn {
        items(notas) { nota ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

                ){
                Column (
                    modifier= Modifier.padding(16.dp)
                ){
                    Text(text = nota)
                    
            }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NotasCardPreview() {
    AppNotasTheme {
        NotasCard()
    }
}