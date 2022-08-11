package com.eastream.eastream.screens.userprofile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.*
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.search.SearchViewModel
import com.eastream.eastream.screens.search.SearchWidgetState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalFoundationApi
@Composable
fun UserProfileScreen(
        navController: NavController = NavController(context = LocalContext.current),
        viewModel: UserProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
        searchViewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val networks = viewModel.networks
        val scrollState = rememberScrollState()
        val searchWidgetState by searchViewModel.searchWidgetState
        val searchTextState by searchViewModel.searchTextState

        Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                        if (searchWidgetState == SearchWidgetState.CLOSED){
                                AppBar(
                                        showSearch = true,
                                        showNetworks = true,
                                        showMenu = true,
                                        onIconClick = { scope.launch { scaffoldState.drawerState.open() } },
                                        navController = navController,
                                        onSearchTriggered = {
                                                searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                                        }, header = "Profile") }
                        else {
                                SearchAppBar(
                                        text = searchTextState,
                                        onTextChange = { searchViewModel.updateSearchTextState(newValue = it.titleWords()) },
                                        onCloseClicked = {
//                                                searchViewModel.updateTextWidgetState(newValue = "")
                                                searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                                        },
                                        onSearchClicked = {
                                                navController.navigate(EastreamScreens.SearchScreen.name + "/$it")
                                                searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                                        }
                                ) } },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                drawerContent = {
                        DrawerHeader()
                        MenuDrawerBody(navController, scaffoldState)
                },
                drawerBackgroundColor = MaterialTheme.colors.background
        ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                        Column(modifier = Modifier.verticalScroll(scrollState)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                        Icon(imageVector = Icons.Outlined.Person, contentDescription = "Avatar", modifier = Modifier
                                                .size(150.dp)
                                                .fillMaxWidth())
                                }

                                Text(text = "Favorites", style = MaterialTheme.typography.h4, modifier = Modifier.padding(16.dp))

                                networks.forEach { network ->
                                        val networkShows = mutableStateOf(listOf<BasicTitleInfo>())
                                        NetworkCardsList(
                                                network,
                                                viewModel,
                                                listOfTitles = networkShows,
                                                navController,
                                        )
                                }
                        }

                }
        }
}

//create true title casing
fun String.titleWords(): String = split(" ").map { it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
        ) else it.toString()
} }.joinToString(" ")

