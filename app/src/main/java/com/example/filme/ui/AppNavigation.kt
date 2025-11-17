package com.example.filme.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filme.repository.MovieRepository
import com.example.filme.viewmodel.GenreViewModel
import com.example.filme.viewmodel.MovieViewModel
import com.example.filme.viewmodel.ViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val repository = MovieRepository()
    val factory = ViewModelFactory(repository)

    NavHost(navController = navController, startDestination = "genreSelection") {
        composable("genreSelection") {
            val genreViewModel: GenreViewModel = viewModel(factory = factory)
            GenreSelectionScreen(navController = navController, viewModel = genreViewModel)
        }
        composable(
            route = "recommendation/{genre}",
            arguments = listOf(navArgument("genre") { type = NavType.StringType })
        ) { backStackEntry ->
            val genre = backStackEntry.arguments?.getString("genre") ?: ""
            val movieViewModel: MovieViewModel = viewModel(factory = factory)
            RecommendationScreen(navController = navController, viewModel = movieViewModel, genre = genre)
        }
        composable("addMovie") {
            val movieViewModel: MovieViewModel = viewModel(factory = factory)
            AddMovieScreen(navController = navController, viewModel = movieViewModel)
        }
    }
}