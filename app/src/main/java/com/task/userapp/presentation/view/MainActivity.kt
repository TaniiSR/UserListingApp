package com.task.userapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.userapp.presentation.route.MainScreensRoute
import com.task.userapp.presentation.ui.DetailScreen
import com.task.userapp.presentation.ui.MainScreen
import com.task.userapp.ui.theme.UserAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            UserAppTheme {
                val mainUIState by mainViewModel.uiState.collectAsStateWithLifecycle()
                NavHost(
                    navController = navHostController,
                    startDestination = MainScreensRoute.MainScreen.route,
                ) {
                    composable(MainScreensRoute.MainScreen.route) {
                        MainScreen(
                            uiState = mainUIState,
                        ) { user ->
                            mainViewModel.setUser(user)
                            navHostController.navigate(MainScreensRoute.DetailScreen.route)
                        }
                    }
                    composable(MainScreensRoute.DetailScreen.route) {
                        mainViewModel.user?.let {
                            DetailScreen(it) {
                                navHostController.popBackStack()
                                mainViewModel.setUser(null)
                            }
                        }
                    }
                }
            }
        }
    }
}