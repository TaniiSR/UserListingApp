package com.task.userapp.presentation.route

sealed class MainScreensRoute(val route: String) {
    data object MainScreen : MainScreensRoute(MAIN_SCREEN)
    data object DetailScreen : MainScreensRoute(DETAIL_SCREEN)
}

const val MAIN_SCREEN = "main_screen"
const val DETAIL_SCREEN = "detail_screen"