package com.example.effective_testapp.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.effective_testapp.presentation.auth.AuthViewModel
import com.example.effective_testapp.presentation.profile.ProfileViewModel

@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: AuthViewModel , profileViewModel: ProfileViewModel) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        AuthNavGraph(navController = navController, viewModel)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val CATALOG = "catalog_graph"

}