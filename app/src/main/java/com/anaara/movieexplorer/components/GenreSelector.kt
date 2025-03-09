package com.anaara.movieexplorer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anaara.movieexplorer.data.model.Genre
import androidx.compose.foundation.lazy.items


@Composable
fun GenreSelector(
    genres: List<Genre>,
    selectedGenre: String?,
    onGenreSelected: (String?) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // All movies option
        item {
            GenreChip(
                name = "All Movies",
                count = null,
                isSelected = selectedGenre == null,
                onClick = { onGenreSelected(null) }
            )
        }

        // Genre specific options
        items(genres) { genre ->
            GenreChip(
                name = genre.name,
                count = genre.count,
                isSelected = selectedGenre == genre.name,
                onClick = { onGenreSelected(genre.name) }
            )
        }
    }
}
