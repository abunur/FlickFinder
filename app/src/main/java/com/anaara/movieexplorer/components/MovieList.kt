package com.anaara.movieexplorer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anaara.movieexplorer.data.fakeMovies
import com.anaara.movieexplorer.data.model.ImdbMovie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movies: List<ImdbMovie>,
    isLoading: Boolean,
    onLoadMore: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(movies) { index, movie ->
            // Trigger load more when reaching the end
            if (index >= movies.size - 5 && !isLoading) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }

            MovieCard(
                title = movie.title,
                year = movie.getReleaseYear(),
                overview = movie.overview,
                genres = movie.genres,
                url = movie.url
            )
        }

        // Loading indicator
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieList(
        movies = fakeMovies,
        isLoading = false,
        onLoadMore = {}
    )
}