package com.example.filme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filme.viewmodel.MovieViewModel

@Composable
fun RecommendationScreen(navController: NavController, viewModel: MovieViewModel, genre: String) {
    val movie by viewModel.recommendedMovie.collectAsState()

    LaunchedEffect(genre) {
        viewModel.recommendMovie(genre)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        movie?.let {
            Text("Filme recomendado: ${it.title}")
        } ?: Text("Nenhum filme encontrado para este gênero.")

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}