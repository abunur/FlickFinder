package com.anaara.movieexplorer.data

import com.anaara.movieexplorer.data.model.ImdbMovie

/**
 * Mock data for testing
 */
// Mock Movie Data
//
val fakeMovies = listOf(
    ImdbMovie(
        id = "tt15398776",
        genres = listOf("Biography", "Drama", "History"),
        release_date = "2023-07-21",
        title = "Oppenheimer",
        tagline = "The world forever changes.",
        overview = "The story of J. Robert Oppenheimer's role in the Manhattan Project.",
        url = "https://www.imdb.com/title/tt15398776/"
    ),
    ImdbMovie(
        id = "tt1517268",
        genres = listOf("Comedy", "Fantasy"),
        release_date = "2023-07-21",
        title = "Barbie",
        tagline = "She's everything. He's just Ken.",
        overview = "Barbie and Ken leave Barbie Land and travel to the real world.",
        url = "https://www.imdb.com/title/tt1517268/"
    ),
    ImdbMovie(
        id = "tt0111161",
        genres = listOf("Drama"),
        release_date = "1994-10-14",
        title = "The Shawshank Redemption",
        tagline = "Fear can hold you prisoner. Hope can set you free.",
        overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
        url = "https://www.imdb.com/title/tt0111161/"
    ),
    ImdbMovie(
        id = "tt0468569",
        genres = listOf("Action", "Crime", "Drama"),
        release_date = "2008-07-18",
        title = "The Dark Knight",
        tagline = "Why so serious?",
        overview = "When the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological tests of his ability to fight injustice.",
        url = "https://www.imdb.com/title/tt0468569/"
    ),
    ImdbMovie(
        id = "tt0167260",
        genres = listOf("Action", "Adventure", "Drama"),
        release_date = "2003-12-17",
        title = "The Lord of the Rings: The Return of the King",
        tagline = "The eye of the enemy is moving.",
        overview = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
        url = "https://www.imdb.com/title/tt0167260/"
    ),
    ImdbMovie(
        id = "tt10676048",
        genres = listOf("Action", "Adventure", "Fantasy"),
        release_date = "2023-11-10",
        title = "The Marvels",
        tagline = "Higher. Further. Faster. Together.",
        overview = "Carol Danvers, aka Captain Marvel, has reclaimed her identity from the tyrannical Kree and taken revenge on the Supreme Intelligence. But unintended consequences see Carol shouldering the burden of a destabilized universe.",
        url = "https://www.imdb.com/title/tt10676048/"
    ),
    ImdbMovie(
        id = "tt9362722",
        genres = listOf("Animation", "Action", "Adventure"),
        release_date = "2023-06-02",
        title = "Spider-Man: Across the Spider-Verse",
        tagline = "It's how you wear the mask that matters.",
        overview = "Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting its very existence. When the heroes clash on how to handle a new threat, Miles must redefine what it means to be a hero.",
        url = "https://www.imdb.com/title/tt9362722/"
    ),
    ImdbMovie(
        id = "tt6791350",
        genres = listOf("Action", "Adventure", "Comedy"),
        release_date = "2023-05-05",
        title = "Guardians of the Galaxy Vol. 3",
        tagline = "Once more with feeling.",
        overview = "Still reeling from the loss of Gamora, Peter Quill rallies his team to defend the universe and one of their own - a mission that could mean the end of the Guardians if not successful.",
        url = "https://www.imdb.com/title/tt6791350/"
    )
)

