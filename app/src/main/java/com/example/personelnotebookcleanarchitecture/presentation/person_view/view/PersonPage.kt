package com.example.personelnotebookcleanarchitecture.presentation.person_view.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.person_view.PersonViewModel

@Composable
fun PersonPage(
    navController: NavController,
    viewModel:PersonViewModel= hiltViewModel()
) {

    Column (modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
         Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.logOut()
                navController.navigate(Screen.LoginPage.route)
        }) {
            Text(text = "LogOut")
        }

    }

}