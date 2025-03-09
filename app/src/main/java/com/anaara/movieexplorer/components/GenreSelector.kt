package com.anaara.movieexplorer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.anaara.movieexplorer.data.model.Genre

@Composable
fun GenreSelector(
    genres: List<Genre>,
    selectedGenre: String?,
    onGenreSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val displayText = when {
        selectedGenre == null -> "All Genres"
        else -> genres.find { it.name == selectedGenre }?.let { "${it.name} (${it.count})" } ?: selectedGenre
    }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(displayText, modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Toggle dropdown"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            // All Genres option
            DropdownMenuItem(
                text = { Text("All Genres") },
                onClick = {
                    onGenreSelected(null)
                    expanded = false
                },
                modifier = Modifier.background(
                    if (selectedGenre == null) MaterialTheme.colorScheme.primaryContainer
                    else Color.Transparent
                )
            )
            // Genres
            genres.forEach { genre ->
                DropdownMenuItem(
                    text = { Text("${genre.name} (${genre.count})") },
                    onClick = {
                        onGenreSelected(genre.name)
                        expanded = false
                    },
                    modifier = Modifier.background(
                        if (genre.name == selectedGenre) MaterialTheme.colorScheme.primaryContainer
                        else Color.Transparent
                    )
                )
            }
        }
    }
}

// Preview Provider
class GenreSelectorPreviewProvider : PreviewParameterProvider<List<Genre>> {
    override val values: Sequence<List<Genre>> = sequenceOf(
        listOf(
            Genre("Action", 6583),
            Genre("Adventure", 3491),
            Genre("Comedy", 13136)
        ),
        listOf(
            Genre("Drama", 20217),
            Genre("Sci-Fi", 3042)
        ),
        emptyList() // No genres
    )
}

// Previews 
@Preview(name = "GenreSelector - All Genres", group = "GenreSelector")
@Composable
fun GenreSelectorPreviewAll(@PreviewParameter(GenreSelectorPreviewProvider::class) genres: List<Genre>) {
    GenreSelector(
        genres = genres,
        selectedGenre = null,
        onGenreSelected = {},
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(name = "GenreSelector - Selected Genre", group = "GenreSelector")
@Composable
fun GenreSelectorPreviewSelected(@PreviewParameter(GenreSelectorPreviewProvider::class) genres: List<Genre>) {
    GenreSelector(
        genres = genres,
        selectedGenre = "Comedy",
        onGenreSelected = {},
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(name = "GenreSelector - Empty Genres", group = "GenreSelector")
@Composable
fun GenreSelectorPreviewEmpty(@PreviewParameter(GenreSelectorPreviewProvider::class) genres: List<Genre>) {
    GenreSelector(
        genres = genres,
        selectedGenre = null,
        onGenreSelected = {},
        modifier = Modifier.padding(8.dp)
    )
}