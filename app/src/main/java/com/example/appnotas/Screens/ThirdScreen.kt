package com.example.appnotas.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.appnotas.ui.theme.ComposeDateTimePickerTheme
import com.example.appnotas.Screens.MaterialDialog
import com.example.appnotas.Screens.datetime.date.DatePickerDefaults
import com.example.appnotas.Screens.datetime.date.datepicker
import com.example.appnotas.Screens.datetime.time.timepicker
import com.example.appnotas.Screens.rememberMaterialDialogState



@Composable
fun DateTimePickerScreen() {
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    var pickedTime by remember { mutableStateOf(LocalTime.NOON) }
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
            Text(text = "Pick date")
        }
        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = formattedTime)
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                // Handle date selection
                dateDialogState.hide()
            }
            negativeButton(text = "Cancel") {
                dateDialogState.hide()
            }
        }
    ) {
        datepicker(
            initialDate = pickedDate,
            title = "Pick a date",
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
            positiveButton(text = "Ok") {
                // Handle time selection
                timeDialogState.hide()
            }
            negativeButton(text = "Cancel") {
                timeDialogState.hide()
            }
        }
    ) {
        timepicker(
            initialTime = pickedTime,
            title = "Pick a time",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ) {
            pickedTime = it
        }
    }
}

@Composable
fun ComposeDateTimePickerApp() {
    ComposeDateTimePickerTheme {
        DateTimePickerScreen()
    }
}

@Composable
fun ComposeDateTimePickerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}
