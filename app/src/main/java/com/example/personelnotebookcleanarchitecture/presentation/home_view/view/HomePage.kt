package com.example.personelnotebookcleanarchitecture.presentation.home_view.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.personelnotebookcleanarchitecture.R
import com.example.personelnotebookcleanarchitecture.Screen
import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.presentation.home_view.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedNote = remember { mutableStateOf<Notes?>(null) }
    val stateNote by viewModel.state.collectAsState()
    val tfSearching = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.getNotes()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (isSearching.value) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = tfSearching.value,
                        onValueChange = {
                            tfSearching.value = it
                            viewModel.searchNotes(it)
                        },
                        label = { Text("Search") }
                    )
                    IconButton(onClick = {
                        tfSearching.value = ""
                        isSearching.value = false
                        viewModel.getNotes()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Close Search"
                        )
                    }
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "NoteApp", fontSize = 24.sp)
                    Icon(
                        modifier = Modifier.size(30.dp)
                            .clickable { isSearching.value = true },
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search"
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(stateNote.noteList) { note ->
                    Card(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = note.title, fontSize = 20.sp)
                                IconButton(onClick = {
                                    viewModel.deleteNote(note)
                                    viewModel.getNotes()
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "")
                                }
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = note.content, fontSize = 20.sp)
                                IconButton(onClick = {
                                    selectedNote.value = note
                                    showDialog.value = true
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.edit), contentDescription = "")
                                }
                            }
                        }
                    }
                }
            }
        }
        Icon(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clickable {
                    selectedNote.value = null
                    showDialog.value = true
                },
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Add"
        )
    }

    if (showDialog.value) {
        AddNoteDialog(
            notes = selectedNote.value,
            onDismiss = { showDialog.value = false },
            onSave = { title, content ->
                if (selectedNote.value != null) {
                    // Mevcut notu güncelle
                    val updatedNote = selectedNote.value!!.copy(title = title, content = content)
                    viewModel.updateNote(updatedNote)
                } else {
                    // Yeni not ekle
                    viewModel.addNote(Notes(title = title, content = content))
                }
                viewModel.getNotes()
                showDialog.value = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (stateNote.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(50.dp)
            )
        }
    }
}

@Composable
fun AddNoteDialog(
    notes: Notes?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    val title = remember { mutableStateOf(notes?.title ?: "") }
    val content = remember { mutableStateOf(notes?.content ?: "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = if (notes == null) "Add Note" else "Edit Note") },
        text = {
            Column {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Note Title") }
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    label = { Text("Note Content") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(title.value, content.value)
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}











