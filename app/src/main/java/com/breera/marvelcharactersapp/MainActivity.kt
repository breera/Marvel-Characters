package com.breera.marvelcharactersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.breera.character_feature.presentation.detail.DetailAction
import com.breera.character_feature.presentation.detail.DetailScreenRoot
import com.breera.character_feature.presentation.detail.DetailVM
import com.breera.character_feature.presentation.home.HomeScreenRoot
import com.breera.character_feature.presentation.shareddata.ShareViewModel
import com.breera.theme.theme.MarvelCharactersAppTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelCharactersAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.CharacterGraph) {
                    navigation<Route.CharacterGraph>(startDestination = Route.CharacterHome) {
                        composable<Route.CharacterHome>(
                            exitTransition = { slideOutHorizontally() },
                            popEnterTransition = { slideInHorizontally() }
                        ) {
                            val shareViewModel =
                                it.sharedKoinViewModel<ShareViewModel>(navController)
                            LaunchedEffect(true) {
                                shareViewModel.onSelectCharacter(null)
                            }
                            HomeScreenRoot { character ->
                                shareViewModel.onSelectCharacter(character)
                                navController.navigate(Route.CharacterDetail)
                            }
                        }

                        composable<Route.CharacterDetail>(
                            enterTransition = {
                                slideInHorizontally { initialOffset ->
                                    initialOffset
                                }
                            },
                            exitTransition = {
                                slideOutHorizontally { initialOffset ->
                                    initialOffset
                                }
                            }
                        ) { entry ->
                            val viewModel = koinViewModel<DetailVM>()
                            val selectedCharacterViewModel =
                                entry.sharedKoinViewModel<ShareViewModel>(navController)
                            val selectedBook by selectedCharacterViewModel.selectedCharacter.collectAsStateWithLifecycle()
                            LaunchedEffect(selectedBook) {
                                selectedBook?.let {
                                    viewModel.onAction(DetailAction.OnSelectedCharacterChange(it))
                                }
                            }
                            DetailScreenRoot(
                                viewModel
                            ) {
                                when (it) {
                                    DetailAction.OnBackClick -> {
                                        navController.navigateUp()
                                    }

                                    DetailAction.OnCategoryClick -> TODO()
                                    is DetailAction.OnSelectedCharacterChange -> TODO()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) { navController.getBackStackEntry(navGraphRoute) }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}

