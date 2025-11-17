package com.example.filme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filme.viewmodel.MovieViewModel

@Composable
fun AddMovieScreen(navController: NavController, viewModel: MovieViewModel) {
    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Adicionar novo filme:")
        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        TextField(value = genre, onValueChange = { genre = it }, label = { Text("Gênero") })
        Button(onClick = {
            viewModel.addMovie(title, genre)
            navController.popBackStack()
        }) {
            Text("Adicionar")
        }
    }
}