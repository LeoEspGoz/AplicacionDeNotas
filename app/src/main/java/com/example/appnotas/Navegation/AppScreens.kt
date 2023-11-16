package com.example.appnotas.Navegation

sealed class AppScreens(val route: String){
    object NotasList: AppScreens("Notas_List")
    object EditarNotas: AppScreens("Editar_Notas")
    object AgregarNotas: AppScreens("Agregar_Notas")
}
