package com.example.filme.repository

import com.example.filme.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MovieRepository {

    private val db = FirebaseFirestore.getInstance()
    private val moviesCollection = db.collection("movies")

    suspend fun getRandomMovie(genre: String): Movie? {
        return try {
            val snapshot = moviesCollection.whereEqualTo("genre", genre).get().await()
            val movies = snapshot.toObjects(Movie::class.java)
            if (movies.isNotEmpty()) {
                movies.random()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addMovie(movie: Movie) {
        moviesCollection.add(movie).await()
    }

    suspend fun getGenres(): List<String> {
        return try {
            val snapshot = moviesCollection.get().await()
            val movies = snapshot.toObjects(Movie::class.java)
            movies.map { it.genre }.distinct()
        } catch (e: Exception) {
            emptyList()
        }
    }
}