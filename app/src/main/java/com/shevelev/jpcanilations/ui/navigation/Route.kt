package com.shevelev.jpcanilations.ui.navigation

sealed interface Route {
    data object MainMenu: Route

    data object GlitchButton: Route

    data object CardRotation: Route

    data object DraggingCard: Route

    data object CircleDiagram: Route

    data object FuzzyList: Route
}