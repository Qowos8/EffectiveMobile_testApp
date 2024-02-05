package com.example.effective_testapp.navigation
enum class Screens{
    AUTH,
    HOME,
    CATALOG,
    ROOT
}
sealed class NavScreen(val route: String) {
    object Auth: NavScreen(Screens.AUTH.name)
    object Catalog: NavScreen(Screens.CATALOG.name)
    object Root: NavScreen(Screens.ROOT.name)
}