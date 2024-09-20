package com.example.personelnotebookcleanarchitecture.presentation.person_view.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PersonPage(modifier: Modifier = Modifier) {

    Column (modifier=Modifier.fillMaxSize()){
        Text(text = "Merhaba")
    }

}