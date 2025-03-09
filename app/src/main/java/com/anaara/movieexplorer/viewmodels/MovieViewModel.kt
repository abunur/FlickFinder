package com.anaara.movieexplorer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaara.movieexplorer.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    // Selected genre
    private val _selectedGenre = MutableStateFlow<String?>(null)
    val selectedGenre = _selectedGenre.asStateFlow()

    // Genres list
    val genres = repository.getGenres()

    // Current page for pagination
    private val _currentPage = MutableStateFlow(0)

    // Combine selected genre and current page to get movies
    @OptIn(ExperimentalCoroutinesApi::class)
    val movies = combine(_selectedGenre, _currentPage) { genre, page ->
        Pair(genre, page)
    }.flatMapLatest { (genre, page) ->
        repository.getMovies(genre, page)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Init
    init {
        refreshData()
    }

    // Refresh data
    fun refreshData() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.refreshGenres()
            repository.refreshMovies(_selectedGenre.value)
            _isLoading.value = false
        }
    }

    // Select genre
    fun selectGenre(genre: String?) {
        viewModelScope.launch {
            _currentPage.value = 0
            _selectedGenre.value = genre
            repository.refreshMovies(genre)
        }
    }

    // Load more movies (pagination)
    fun loadMoreMovies() {
        if (!_isLoading.value) {
            viewModelScope.launch {
                _isLoading.value = true
                _currentPage.value += 1
                _isLoading.value = false
            }
        }
    }

    // Reset to first page
    fun resetPagination() {
        _currentPage.value = 0
    }
}