package com.example.personelnotebookcleanarchitecture.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.home_view.view.HomePage
import com.example.personelnotebookcleanarchitecture.presentation.person_view.view.PersonPage
import com.example.personelnotebookcleanarchitecture.presentation.register_view.view.LoginPage
import com.example.personelnotebookcleanarchitecture.presentation.register_view.view.RegisterPage
import com.google.firebase.auth.FirebaseAuth
import com.example.personelnotebookcleanarchitecture.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PageController() {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    var startDestination by remember { mutableStateOf(Screen.RegisterPage.route) }

    val items = listOf("HomePage", "Person")
    val currentItem = remember { mutableStateOf(0) }

    LaunchedEffect(currentUser) {
        startDestination = if (currentUser != null) {
            Screen.HomePage.route
        } else {
            Screen.RegisterPage.route
        }
    }

    val controller = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentItem.value == index,
                        onClick = {
                            currentItem.value = index
                            controller.navigate(
                                when (item) {
                                    "HomePage" -> Screen.HomePage.route
                                    "Person" -> Screen.PersonPage.route
                                    else -> Screen.HomePage.route
                                }
                            )
                        },
                        label = { Text(text = item) },
                        icon = {
                            when (item) {
                                "HomePage" -> Icon(painter = painterResource(id = R.drawable.home), contentDescription = "")
                                "Person" -> Icon(painter = painterResource(id = R.drawable.person), contentDescription = "")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = controller,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.RegisterPage.route) {
                RegisterPage(navController = controller)
            }
            composable(Screen.LoginPage.route) {
                LoginPage(navController = controller)
            }
            composable(Screen.HomePage.route) {
                HomePage(navController = controller)
            }
            composable(Screen.PersonPage.route) {
                PersonPage()
            }
        }
    }
}


