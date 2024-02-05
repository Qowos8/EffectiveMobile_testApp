package com.example.effective_testapp.navigation.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.effective_testapp.R

data class BottomNavItem (
    val name: String,
    val route: String,
    val icon: Painter,
    val badgeCount: Int = 0
)

@Composable
fun bottomNavigationItems() : List<BottomNavItem> {

    return listOf(
        BottomNavItem(
            name = "Главная",
            icon = painterResource(id = R.drawable.home),
            //ImageVector.vectorResource(id = R.drawable.home),
            route = BottomNavScreen.Main.route
        ),
        BottomNavItem(
            name = "Каталог",
            icon = painterResource(id = R.drawable.catalog),
            //ImageVector.vectorResource(id = R.drawable.catalog),
            route = BottomNavScreen.Catalog.route
        ),

        BottomNavItem(
            name = "Корзина",
            icon = painterResource(id = R.drawable.basket),
            //ImageVector.vectorResource(id = R.drawable.basket),
            route = BottomNavScreen.Basket.route
        ),
        BottomNavItem(
            name = "Акции",
            icon = painterResource(id = R.drawable.discont),
            //ImageVector.vectorResource(id = R.drawable.discont),
            route = BottomNavScreen.Discont.route
        ),
        BottomNavItem(
            name = "Профиль",
            icon = painterResource(id = R.drawable.profile,),
            //ImageVector.vectorResource(id = R.drawable.profile),
            route = BottomNavScreen.Profile.route
        )
    )
}
