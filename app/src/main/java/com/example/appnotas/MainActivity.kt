package com.example.appnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.appnotas.Navegation.AppNavigation
import com.example.appnotas.Room.NotasDatabase
import com.example.appnotas.ViewModels.NotesViewModel
import com.example.appnotas.data.NotesDataBase
import com.example.appnotas.screens.AddNoteScreen
import com.example.appnotas.screens.NotesScreen

import com.example.appnotas.ui.theme.AppNotasTheme

class MainActivity : ComponentActivity() {
    private val database by lazy{
        Room.databaseBuilder(
            applicationContext,
            NotesDataBase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NotesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>):T{
                    return NotesViewModel(database.dao) as T
                }

            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                        ){
                           val state by viewModel.state.collectAsState()
                           val navController= rememberNavController()
                           NavHost(navController= navController, startDestination= "NotesScreen"){
                               composable("NotesScreen"){
                                   NotesScreen(
                                       state = state,
                                       navController= navController,
                                       onEvent= viewModel::onEvent

                                   )
                               }
                               composable("AddNoteScreen"){
                                AddNoteScreen(
                                    state= state,
                                    navController = navController,
                                    onEvent= viewModel::onEvent
                                )
                               }
                           }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppNotasTheme {
    }
}