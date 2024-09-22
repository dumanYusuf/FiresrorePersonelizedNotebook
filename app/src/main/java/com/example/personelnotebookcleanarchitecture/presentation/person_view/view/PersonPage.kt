package com.example.personelnotebookcleanarchitecture.presentation.person_view.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.presentation.person_view.PersonViewModel

@Composable
fun PersonPage(
    navController: NavController,
    viewModel: PersonViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getUser()
    }

    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.value.isSucsess.forEach { user ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.profilUrl),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${user.userName} ${user.userLastName}",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Kullanıcı E-postası
                Text(
                    text = user.email,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Button(
            onClick = {
                viewModel.logOut()
                navController.navigate(Screen.LoginPage.route)
            }
        ) {
            Text(text = "Log Out")
        }
    }
}
