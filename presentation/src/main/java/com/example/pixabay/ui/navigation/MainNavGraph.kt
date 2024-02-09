package com.example.pixabay.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pixabay.ui.screen.detail.DetailScreen
import com.example.pixabay.ui.screen.search.SearchScreen
import com.example.pixabay.ui.screen.splash.SplashScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MainNavRoute.Splash.path
    ) {
        addSplashScreen(navController, this)

        addSearchScreen(navController, this)

        addDetailScreen(navController, this)
    }
}

private fun addSplashScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = MainNavRoute.Splash.path) {
        BackHandler(true) {}

        SplashScreen(
            navigateToSearchScreen = {
                navController.navigate(MainNavRoute.Search.path)
            }
        )
    }
}

private fun addSearchScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = MainNavRoute.Search.path) {
        BackHandler(true) {}

        SearchScreen(
            navigateToDetailScreen = { imageId ->
                navController.navigate(
                    MainNavRoute.Detail.withArgs(imageId)
                )
            }
        )
    }
}

private fun addDetailScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = MainNavRoute.Detail.withArgsFormat(
            MainNavRoute.Detail.imageId,
        ),
        arguments = listOf(
            navArgument(MainNavRoute.Detail.imageId) {
                type = NavType.StringType
            },
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments

        DetailScreen(
            imageId = args?.getString(MainNavRoute.Detail.imageId).orEmpty(),
            onBackClick = { navController.popBackStack() },
        )
    }
}