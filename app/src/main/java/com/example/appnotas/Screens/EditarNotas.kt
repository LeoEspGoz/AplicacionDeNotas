package com.example.appnotas.Screens


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appnotas.Data.Notas
import com.example.appnotas.ViewModels.notasViewModel
@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun EditarNotas(navController: NavController, viewModel: notasViewModel, id: Int, titulo: String?, descripcion: String?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Editar view", color = Color.White, fontWeight = FontWeight.Bold)
            },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(imageVector =Icons.Default.ArrowBack, contentDescription = "Regresar", tint= Color.White )
                    }
                }
            )
        }
    ) {
        contentEditarView(it, navController, viewModel, id, titulo, descripcion)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentEditarView(it: PaddingValues, navController: NavController, viewModel: notasViewModel, id: Int, titulo: String?,descripcion: String?){
    var titulo by remember{ mutableStateOf(titulo) }
    var descripcion by remember { mutableStateOf(descripcion) }

    Column(
        modifier= Modifier
            .padding(it)
            .padding(top = 38.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = titulo?:"",
            onValueChange ={titulo=it} ,
            label={ Text(text = "titulo")},
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(value = descripcion?: "",
            onValueChange ={descripcion=it} ,
            label={ Text(text = "descripcion")},
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        Button(onClick = {
            val nota= Notas(id= id, Titulo= titulo!!, Descripcion= descripcion!!)
            viewModel.actualizarNota(nota)
            navController.popBackStack()

        }) {
            Text(text = "Editar")

        }
    }



}



