package com.example.appnotas.Navegation

sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens("first_Screen")
    object SecondScreen: AppScreens("second_Screen")
}
