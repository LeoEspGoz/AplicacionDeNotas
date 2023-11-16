package com.example.appnotas.Navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appnotas.Screens.AgregarNotas
import com.example.appnotas.Screens.EditarNotas
import com.example.appnotas.Screens.NotasList
import com.example.appnotas.ViewModels.notasViewModel

@Composable
fun AppNavigation(viewModel: notasViewModel){
   val navController= rememberNavController()
   NavHost(navController =navController , startDestination ="inicio"){
       composable("inicio"){
           NotasList(navController, viewModel)
       }

       composable("agregar"){
           AgregarNotas(navController, viewModel)
       }
       composable("editar/ ¨{id}/{titulo}/{descripcion}", arguments = listOf(
           navArgument("id"){type= NavType.IntType},
           navArgument("titulo"){type= NavType.StringType},
           navArgument("descripcion"){type= NavType.StringType}
       )){
           EditarNotas(
               navController,
               viewModel,
               it.arguments!!.getInt("id"),
               it.arguments?.getString("titulo"),
               it.arguments?.getString("descripcion")
           )
       }

   }

}