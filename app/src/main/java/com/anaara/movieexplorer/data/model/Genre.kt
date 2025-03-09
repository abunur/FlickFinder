package com.anaara.movieexplorer.data.model

data class Genre(
    val name: String,
    val count: Int
)

//// Data class to represent the list of genres (entire API response)
//data class GenreResponse(
//    val genres: List<Genre>
//) {
//    companion object {
//        // Factory method to parse the API response format
//        fun fromApiResponse(apiResponseArray: List<List<Any>>): GenreResponse {
//            val genreList = apiResponseArray.map { item ->
//                Genre(
//                    name = item[0] as String,
//                    count = (item[1] as Number).toInt()
//                )
//            }
//            return GenreResponse(genreList)
//        }
//    }
//}