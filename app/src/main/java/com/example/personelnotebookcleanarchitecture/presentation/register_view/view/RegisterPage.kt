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
import androidx.navigation.NavController
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.register_view.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel= hiltViewModel()
) {
    val state=viewModel.authStat.collectAsState().value
    var tfEmail by remember { mutableStateOf("") }
    var tfUserName by remember { mutableStateOf("") }
    var tfUserLastName by remember { mutableStateOf("") }
    var tfPassword by  remember { mutableStateOf("") }
    var tfConfirmPassword by  remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }



    fun validateAndRegister() {
        errorMessage = ""

        if (!tfEmail.contains("@")) {
            errorMessage = "Invalid email address. Must contain '@'."
            return
        }
        if (tfUserName.isBlank()) {
            errorMessage = "UserName cannot be empty."
            return
        }
        if (tfUserLastName.isBlank()) {
            errorMessage = "UserLastName cannot be empty."
            return
        }
        if (tfPassword.length < 6) {
            errorMessage = "Password must be at least 6 characters."
            return
        }
        if (tfPassword != tfConfirmPassword) {
            errorMessage = "Passwords do not match."
            return
        }
        viewModel.register(tfEmail, tfPassword,tfUserName,tfUserLastName)
    }


    LaunchedEffect(state.isSucsess) {
        if (state.isSucsess) {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.RegisterPage.route) { inclusive = true }
            }
        }
    }


    Scaffold(
        content = {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                CustomTextField(
                    value = tfEmail,
                    onValueChange ={tfEmail=it} ,
                    label = "Enter Email",
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))

                CustomTextField(
                    value = tfUserName,
                    onValueChange ={tfUserName=it} ,
                    label = "Enter UserName",
                    modifier = Modifier.padding(10.dp)
                )
                CustomTextField(
                    value = tfUserLastName,
                    onValueChange ={tfUserLastName=it} ,
                    label = "Enter UserLatName",
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                CustomTextField(
                    value = tfPassword,
                    onValueChange ={tfPassword=it} ,
                    label = "Enter Password",
                    modifier = Modifier.padding(10.dp),
                    isPassword = true
                )
                Spacer(modifier = Modifier.padding(10.dp))
                CustomTextField(
                    value = tfConfirmPassword,
                    onValueChange ={tfConfirmPassword=it} ,
                    label = "Enter Confirm Password",
                    modifier = Modifier.padding(10.dp),
                    isPassword = true
                )
                Spacer(modifier = Modifier.padding(10.dp))

               Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                   Button(
                       modifier = Modifier.align(Alignment.CenterVertically),
                       onClick = {
                       // register button
                      // viewModel.register(tfEmail,tfPassword)
                           validateAndRegister()
                   }) {
                       Text(text = "Register")
                   }
                   Spacer(modifier = Modifier.padding(5.dp))
                   TextButton(onClick = {
                       // go to login
                       navController.navigate(Screen.LoginPage.route)
                   }) {
                       Text(text = "do you have an account?")
                   }
               }


                Spacer(modifier = Modifier.size(15.dp))

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