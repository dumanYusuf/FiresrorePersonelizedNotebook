package com.example.personelnotebookcleanarchitecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.home_view.view.HomePage
import com.example.personelnotebookcleanarchitecture.presentation.person_view.view.PersonPage
import com.example.personelnotebookcleanarchitecture.presentation.register_view.view.LoginPage
import com.example.personelnotebookcleanarchitecture.presentation.register_view.view.RegisterPage


@Composable
fun PageController() {
    val controller= rememberNavController()
    NavHost(navController = controller, startDestination = Screen.RegisterPage.route) {
        composable(Screen.RegisterPage.route){
            RegisterPage(navController = controller)
        }
        composable(Screen.LoginPage.route){
            LoginPage(navController = controller)
        }
        composable(Screen.HomePage.route){
            HomePage()
        }
        composable(Screen.PersonPage.route){
            PersonPage()
        }
    }
}
