package com.anaara.movieexplorer.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieCard(
    title: String = "[Title]",
    year: Int = 1986,
    overview: String = "Overview",
    genres: List<String> = listOf("Genre 1", "Genre 2"),
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Movie Title and Year
            Row (
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                }
                Column {
                    Text(
                        text = "($year)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.End
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Text(
                text = overview,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.align(Alignment.End)) {
                Text(
                    text = genres.joinToString(", "),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Composable
fun MovieCardPreviewLight() {
    MovieCard(
        title = "Oppenheimer",
        year = 2023,
        overview = "The story of J. Robert Oppenheimer's role in the Manhattan Project that led to the development of the atomic bomb during World War II.",
        genres = listOf("Biography", "Drama", "History")
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieCardPreviewDark() {
    MovieCard(
        title = "Oppenheimer",
        year = 2023,
        overview = "The story of J. Robert Oppenheimer's role in the Manhattan Project that led to the development of the atomic bomb during World War II.",
        genres = listOf("Biography", "Drama", "History")
    )
}

@Preview(name = "Long Title")
@Composable
fun MovieCardPreviewLongTitle() {
    MovieCard(
        title = "The Lord of the Rings: The Return of the King",
        year = 2003,
        overview = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
        genres = listOf("Action", "Adventure", "Drama")
    )
}