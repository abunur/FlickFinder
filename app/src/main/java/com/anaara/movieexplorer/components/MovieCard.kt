package com.anaara.movieexplorer.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "($year)",
                    fontSize = 16.sp
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Text(
                text = overview,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.align(Alignment.End)) {
                Text(
                    text = genres.joinToString(", "),
                    fontSize = 8.sp
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