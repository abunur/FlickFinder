package com.anaara.movieexplorer.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anaara.movieexplorer.data.fakeMovies
import com.anaara.movieexplorer.data.model.ImdbMovie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movies: List<ImdbMovie> = emptyList(),
    isLoading: Boolean = false,
    onMovieClick: (ImdbMovie) -> Unit = {},
    onLoadMore: () -> Unit = {},
    onGenreSelected: (String?) -> Unit = {}
) {



    var selectedGenre by remember { mutableStateOf("All") }
    val filteredMovies = if (selectedGenre == "All") {
        movies
    } else {
        movies.filter { it.genres.contains(selectedGenre) }
    }

    val availableGenres = listOf("All") + movies.flatMap { it.genres }.distinct()
                // Genre Filter
                var expanded by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                    TextField(
                        readOnly = true,
                        value = selectedGenre,
                        onValueChange = {},
                        label = { Text("Genre") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        availableGenres.forEach { genre ->
                            DropdownMenuItem(
                                text = { Text(genre) },
                                onClick = {
                                    selectedGenre = genre
                                    expanded = false
                                },
                                contentPadding = PaddingValues(16.dp)
                            )
                        }
                    }
                }



                // Movie List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(filteredMovies) { movie ->
                        MovieCard(
                            title = movie.title,
                            year = movie.getReleaseYear(),
                            overview = movie.overview,
                            genres = movie.genres
                        )
                        Spacer(modifier = Modifier.height(16.dp)) // Space between cards
                    }
                }

        }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieList(movies = fakeMovies)
}