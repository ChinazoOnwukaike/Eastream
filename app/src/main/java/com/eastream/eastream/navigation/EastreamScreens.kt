package com.eastream.eastream.navigation

import okhttp3.Route

enum class EastreamScreens {
    SplashScreen,
    TitlesScreen,
    TitleInfoScreen,
    UserProfileScreen,
    LoginScreen,
    RegisterScreen,
    SearchScreen,
    AboutScreen,
    SettingsScreen;

    companion object {
        fun fromRoute(route: String?) : EastreamScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            TitlesScreen.name -> TitlesScreen
            TitleInfoScreen.name -> TitleInfoScreen
            UserProfileScreen.name -> UserProfileScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            SearchScreen.name -> SearchScreen
            AboutScreen.name -> AboutScreen
            SettingsScreen.name -> SettingsScreen
            null -> TitlesScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
