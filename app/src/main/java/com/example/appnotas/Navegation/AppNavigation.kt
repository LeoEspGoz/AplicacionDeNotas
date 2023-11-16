package com.example.appnotas.Navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnotas.Screens.FirstScreen
import com.example.appnotas.Screens.SecondScreen
import com.example.appnotas.Screens.ThirdScreen
import com.example.appnotas.ViewModels.notasViewModel

@Composable
fun AppNavigation(viewModel: notasViewModel){
   val navController= rememberNavController()
   NavHost(navController =navController , startDestination =AppScreens.NotasList.route){
       composable(route= AppScreens.NotasList.route){
           AppScreens.NotasList(navController)
       }
       composable(route= AppScreens.SecondScreen.route){
           EditarNotas(navController)
       }
       composable(route= AppScreens.ThirdScreen.route){
           AgregarNotas(navController)
       }

   }

}