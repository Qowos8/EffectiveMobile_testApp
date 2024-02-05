package com.example.effective_testapp.navigation.root
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.effective_testapp.navigation.bottomNav.BottomNavScreen
import com.example.effective_testapp.presentation.basket.BasketScreen
import com.example.effective_testapp.presentation.catalog.CatalogScreen
import com.example.effective_testapp.presentation.discont.DiscontScreen
import com.example.effective_testapp.presentation.main.MainScreen
import com.example.effective_testapp.presentation.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        route = Graph.CATALOG,
        startDestination = BottomNavScreen.Catalog.route
    ) {
        composable(route = BottomNavScreen.Catalog.route) {
            CatalogScreen(navController = navController)
        }

        composable(route = BottomNavScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = BottomNavScreen.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(route = BottomNavScreen.Discont.route) {
            DiscontScreen(navController = navController)
        }
        composable(route = BottomNavScreen.Main.route) {
            MainScreen(navController = navController)
        }
        //detailsNavGraph(navController = navController)
    }
}
/*fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}*/

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}