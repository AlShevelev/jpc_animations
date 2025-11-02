package com.shevelev.jpcanilations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.shevelev.jpcanilations.features.glitch.GlitchButtonScreen
import com.shevelev.jpcanilations.mainmenu.MainMenu
import com.shevelev.jpcanilations.ui.navigation.Route
import com.shevelev.jpcanilations.ui.navigation.Route.GlitchButton
import com.shevelev.jpcanilations.ui.navigation.Route.MainMenu
import com.shevelev.jpcanilations.ui.theme.JPCAnilationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JPCAnilationsTheme {
                val backStack = remember { mutableStateListOf<Route>(MainMenu) }

                NavDisplay(
                    entryDecorators = listOf(
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is MainMenu -> NavEntry(key) {
                                MainMenu(
                                    backstack = backStack,
                                )
                            }

                            is GlitchButton -> NavEntry(key) {
                                GlitchButtonScreen()
                            }
                        }
                    }
                )
            }
        }
    }
}
