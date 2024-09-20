package com.example.personelnotebookcleanarchitecture.presentation.home_view.view

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage() {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Welcome HomePage")
            })
        },
        content = {

        }
    )
}