package com.example.appnotas.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appnotas.Data.Notas
import com.example.appnotas.Navegation.AppScreens
import com.example.appnotas.ViewModels.notasViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

fun AgregarNotas(navController: NavController, viewModel: notasViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Agregar view", color = Color.White, fontWeight = FontWeight.Bold)
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
        contentAgregarView(it, navController, viewModel)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentAgregarView(it: PaddingValues, navController: NavController,viewModel: notasViewModel){
    var titulo by remember{ mutableStateOf(" ") }
    var descripcion by remember { mutableStateOf(" ") }

   Column(
       modifier= Modifier
           .padding(it)
           .padding(top = 38.dp)
           .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
     OutlinedTextField(value = titulo,
         onValueChange ={titulo=it} ,
       label={ Text(text = "titulo")},
         modifier= Modifier
             .fillMaxWidth()
             .padding(horizontal = 30.dp)
             .padding(bottom = 15.dp)
     )
       OutlinedTextField(value = descripcion,
           onValueChange ={descripcion=it} ,
           label={ Text(text = "descripcion")},
           modifier= Modifier
               .fillMaxWidth()
               .padding(horizontal = 30.dp)
               .padding(bottom = 15.dp)
       )
       Button(onClick = {
           val nota= Notas(titulo= titulo, descripcion = descripcion)
           viewModel.agregarNota(nota)
           navController.popBackStack()

       }) {
         Text(text = "Agregar")

       }
   }



}