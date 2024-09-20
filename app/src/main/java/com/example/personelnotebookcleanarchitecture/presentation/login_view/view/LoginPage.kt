package com.example.personelnotebookcleanarchitecture.presentation.register_view.view

import CustomTextField
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.login_view.LoginViewModel
import com.example.personelnotebookcleanarchitecture.presentation.register_view.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel= hiltViewModel()
) {
    val state=viewModel.authStat.collectAsState().value
    var tfEmail by remember { mutableStateOf("") }
    var tfPassword by  remember { mutableStateOf("") }


    LaunchedEffect(state.isSucsess) {
        if (state.isSucsess) {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.LoginPage.route) { inclusive = true }
            }
        }
    }



    Scaffold(
        content = {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                CustomTextField(
                    value = tfEmail,
                    onValueChange = {tfEmail=it},
                    label = "Enter Email",
                    modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.padding(10.dp))

                CustomTextField(
                    value = tfPassword,
                    onValueChange = {tfPassword=it},
                    label = "Enter Password",
                    isPassword = true,
                    modifier = Modifier.padding(10.dp))

                Spacer(modifier = Modifier.padding(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        // register button
                        viewModel.login(tfEmail,tfPassword)
                    }) {
                        Text(text = "Login")
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextButton(onClick = {
                        // go to register
                        navController.navigate(Screen.RegisterPage.route)
                    }) {
                        Text(text = "Log in if you have an account?")
                    }
                }


                // Başarılı kayıt durumu
                if (state.isSucsess) {
                    Text(
                        text = "Registration Successful!",
                        color = Color.Green,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                // Yüklenme durumu
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = Color(0xFF6200EE),
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                // Hata mesajı
                if (state.isError.isNotBlank()) {
                    Text(
                        text = state.isError,
                        color = Color.Red,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                }

            }
        }
    )
}