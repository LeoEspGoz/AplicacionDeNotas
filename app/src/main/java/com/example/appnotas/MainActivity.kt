package com.example.appnotas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appnotas.ui.theme.AppNotasTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteApp()
                 }
            }
        }
    }
}
@Composable
fun NoteApp() {
    var noteText by remember { mutableStateOf("") }
    val notes = remember { mutableStateListOf<String>() }
    var isAddingNote by remember { mutableStateOf(false) }
    var isKeyboardVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (isAddingNote) {
            BasicTextField(
                value = noteText,
                onValueChange = { noteText = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (noteText.isNotBlank()) {
                            notes.add(noteText)
                            noteText = ""
                            isAddingNote = false
                        }
                    }
                ),
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
                    .onFocusChanged { focusState ->
                        isKeyboardVisible = focusState.isFocused
                    }
            )
        } else {
            Button(
                onClick = {
                    isAddingNote = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Nota")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(notes) { note ->
                Text(
                    text = note,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isAddingNote) {
            Button(
                onClick = {
                    if (noteText.isNotBlank()) {
                        notes.add(noteText)
                        noteText = ""
                        isAddingNote = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Nota")
            }
        }

        // Agregar un bloque de espacio en blanco que cubra toda la pantalla y oculte el teclado al hacer clic fuera
        if (isKeyboardVisible) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clickable {
                        // Ocultar el teclado al hacer clic fuera
                        isKeyboardVisible = false
                    }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppNotasTheme {
        NoteApp()
    }
}