package com.anaara.movieexplorer.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class responsible for initializing the dependency injector.
 */
@HiltAndroidApp
class MovieExplorer: Application()