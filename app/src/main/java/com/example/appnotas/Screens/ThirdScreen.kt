package com.example.appnotas.Screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appnotas.ui.theme.AppNotasTheme
import com.example.appnotas.MaterialDialog
import com.example.appnotas.datetime.date.datepicker
import com.example.appnotas.datetime.time.timepicker
import com.example.appnotas.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasTheme {
                var pickedDate by remember {
                    mutableStateOf(LocalDate.now())
                }
                var pickedTime by remember {
                    mutableStateOf(LocalTime.NOON)
                }
                val formattedDate by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("MMM dd yyyy")
                            .format(pickedDate)
                    }
                }
                val formattedTime by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("hh:mm")
                            .format(pickedTime)
                    }
                }

                val dateDialogState = rememberMaterialDialogState()
                val timeDialogState = rememberMaterialDialogState()

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        dateDialogState.show()
                    }) {
                        Text(text = "Selecciona Fecha")
                    }
                    Text(text = formattedDate)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        timeDialogState.show()
                    }) {
                        Text(text = "Selecciona Hora")
                    }
                    Text(text = formattedTime)
                }
                MaterialDialog(
                    dialogState = dateDialogState,
                    buttons = {
                        positiveButton(text = "Listo") {
                            Toast.makeText(
                                applicationContext,
                                "Pesionaste Listo",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Cancelar")
                    }
                ) {
                    datepicker(
                        initialDate = LocalDate.now(),
                        title = "Selecciona una Fecha",
                        allowedDateValidator = {
                            it.dayOfMonth % 2 == 1
                        }
                    ) {
                        pickedDate = it
                    }
                }
                MaterialDialog(
                    dialogState = timeDialogState,
                    buttons = {
                        positiveButton(text = "Listo") {
                            Toast.makeText(
                                applicationContext,
                                "Presionaste Listo",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Cancelar")
                    }
                ) {
                    timepicker(
                        initialTime = LocalTime.NOON,
                        title = "Selecciona una Hora",
                        timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
                    ) {
                        pickedTime = it
                    }
                }
            }
        }
    }
}