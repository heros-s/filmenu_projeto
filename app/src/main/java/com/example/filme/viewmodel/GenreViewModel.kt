package com.example.filme.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.filme.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenreViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres: StateFlow<List<String>> = _genres

    init {
        viewModelScope.launch {
            _genres.value = repository.getGenres()
        }
    }
}