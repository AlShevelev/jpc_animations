package com.shevelev.jpcanilations.ui.navigation

sealed interface Route {
    data object MainMenu: Route

    data object GlitchButton: Route
}