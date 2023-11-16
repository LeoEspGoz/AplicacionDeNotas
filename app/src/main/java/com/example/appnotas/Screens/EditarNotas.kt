package com.example.appnotas.Screens


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirdScreen(navController: NavController){
    Scaffold {
        ThirdBodyContent(navController)

    }

}
@Composable
fun ThirdBodyContent(
    navController: NavController

){
    Column {
       DatePicker()
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(){
  var fecha by rememberSaveable { mutableStateOf("") }
    val anio: Int
    val mes: Int
    val dia: Int
    val nCalendar= Calendar.getInstance()
    anio = nCalendar.get(Calendar.YEAR)
    mes = nCalendar.get(Calendar.MONTH)
    dia = nCalendar.get(Calendar.DAY_OF_MONTH)

    val nDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _Datepicker, anio:Int, mes:Int, dia:Int->
            fecha = "$dia/${mes-1}/$anio"
        },anio,mes,dia
    )
    Box(modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.align(Alignment.Center)){
            OutlinedTextField(
                value = fecha,
                onValueChange= {fecha = it},
                readOnly = true,
                label = { Text(text = "Select Date")}
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .clickable {
                        nDatePickerDialog.show()
                    }
            )
        }

  }
}




