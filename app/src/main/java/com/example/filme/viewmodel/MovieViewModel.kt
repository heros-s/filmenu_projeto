package com.example.filme.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.filme.model.Movie
import com.example.filme.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _recommendedMovie = MutableStateFlow<Movie?>(null)
    val recommendedMovie: StateFlow<Movie?> = _recommendedMovie

    fun recommendMovie(genre: String) {
        viewModelScope.launch {
            _recommendedMovie.value = repository.getRandomMovie(genre)
        }
    }

    fun addMovie(title: String, genre: String) {
        viewModelScope.launch {
            val movie = Movie(title = title, genre = genre)
            repository.addMovie(movie)
        }
    }
}