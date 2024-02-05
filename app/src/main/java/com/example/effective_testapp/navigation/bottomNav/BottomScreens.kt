package com.example.effective_testapp.navigation.bottomNav

enum class BottomScreens{
    CATALOG,
    PROFILE,
    BASKET,
    MAIN,
    DISCONT,
    ROOT,
    HOME
}

sealed class BottomNavScreen(val route: String) {
    object Catalog: BottomNavScreen(BottomScreens.CATALOG.name)
    object Profile: BottomNavScreen(BottomScreens.PROFILE.name)
    object Basket: BottomNavScreen(BottomScreens.BASKET.name)
    object Discont: BottomNavScreen(BottomScreens.DISCONT.name)
    object Main: BottomNavScreen(BottomScreens.MAIN.name)
    object Root: BottomNavScreen(BottomScreens.ROOT.name)
    object Home: BottomNavScreen(BottomScreens.HOME.name)



}