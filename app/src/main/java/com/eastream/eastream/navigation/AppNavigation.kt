package com.eastream.eastream.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eastream.eastream.screens.userprofile.UserProfileScreen
import com.eastream.eastream.screens.about.AboutScreen
import com.eastream.eastream.screens.login.LoginScreen
import com.eastream.eastream.screens.search.SearchScreen
import com.eastream.eastream.screens.splash.SplashScreen
import com.eastream.eastream.screens.titleinfo.TitleInfoScreen
import com.eastream.eastream.screens.titles.TitlesScreen

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = EastreamScreens.SplashScreen.name) {
        composable(EastreamScreens.SplashScreen.name) {
            SplashScreen(navController)
        }

        composable(EastreamScreens.TitlesScreen.name) {
            TitlesScreen(navController)
        }

        val infoName = EastreamScreens.TitleInfoScreen.name
        composable("$infoName/{titleId}", arguments = listOf(navArgument("titleId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("titleId").let {
                TitleInfoScreen(navController = navController, titleId = it.toString())
            }
        }

        composable(EastreamScreens.UserProfileScreen.name) {
            UserProfileScreen(navController)
        }

        composable(EastreamScreens.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(EastreamScreens.AboutScreen.name) {
            AboutScreen(navController)
        }

        val searchName = EastreamScreens.SearchScreen.name
        composable("$searchName/{searchQuery}", arguments = listOf(navArgument("searchQuery"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("searchQuery").let {
                SearchScreen(navController = navController, searchQuery = it.toString())
            }
        }
    }
}
