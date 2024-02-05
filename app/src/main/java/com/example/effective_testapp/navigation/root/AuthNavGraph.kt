package com.example.effective_testapp.navigation.root

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.effective_testapp.navigation.NavScreen
import com.example.effective_testapp.presentation.auth.AuthScreen
import com.example.effective_testapp.presentation.auth.AuthViewModel

fun NavGraphBuilder.AuthNavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = NavScreen.Auth.route
    ) {
        composable(route = NavScreen.Auth.route) {
            AuthScreen(viewModel, navController)
        }
    }
}
