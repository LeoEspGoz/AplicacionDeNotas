package com.example.appnotas.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.appnotas.Componentes.NotasCard
import com.example.appnotas.Navegation.AppScreens
import com.example.appnotas.ViewModels.notasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotasList(navController: NavController, viewModel: notasViewModel){

    Scaffold(
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                Text(text = "Lista de notas", color= Color.White, fontWeight = FontWeight.Bold)
            },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

                )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Nota")
            }
        }

    ) {
        ContentNotasList(it, navController, viewModel)


    }
}

@Composable
fun ContentNotasList(it: PaddingValues, navController: NavController, viewModel: notasViewModel){
    val state = viewModel.state
    Column(
        modifier = Modifier.padding(it)
    ) {
        LazyColumn {
            items(state.listaNotas){
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .padding(18.dp)

                    ) {
                        Text(text= it.titulo)
                        Text(text=it.descripcion)
                        IconButton(onClick = {navController.navigate("editar/${it.id}/${it.titulo}/${it.descripcion}")}) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar" )
                        }
                        IconButton(
                            onClick = {viewModel.eliminarNota(it)}
                        ){
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                        }
                    }
                }


            }
        }
    }
}

