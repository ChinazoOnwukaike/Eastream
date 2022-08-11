
package com.eastream.eastream.screens.titles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.eastream.eastream.components.*
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.search.SearchViewModel
import com.eastream.eastream.screens.search.SearchWidgetState
import com.eastream.eastream.screens.userprofile.titleWords
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun TitlesScreen(navController: NavController,
                 viewModel: TitlesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                 searchViewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val networks = viewModel.networks
    val networkName = viewModel.networkName.value
    val listOfTitles = viewModel.listOfTitles
    val gridState = rememberLazyListState(0)
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState
    val loading = viewModel.loading.value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            when (searchWidgetState) {
                SearchWidgetState.CLOSED -> {
                    AppBar(
                        showSearch = true,
                        showNetworks = true,
                        showMenu = true, onIconClick = { scope.launch { scaffoldState.drawerState.open()} },
                        navController = navController,
                        onSearchTriggered = { searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                        networks = networks,
                        networkName = networkName)
                }
                SearchWidgetState.OPENED -> {
                    SearchAppBar(
                        text = searchTextState,
                        onTextChange = { searchViewModel.updateSearchTextState(newValue = it.titleWords())},
                        onCloseClicked = {
//                            searchViewModel.updateTextWidgetState(newValue = "")
                            searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
                        onSearchClicked = {
                            navController.navigate(EastreamScreens.SearchScreen.name + "/$it")
                            searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                        }
                    )
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            MenuDrawerBody(navController, scaffoldState)
        },
        drawerBackgroundColor = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                state = gridState,
                content = {
                    viewModel.getTitles(networkName)

                    itemsIndexed(listOfTitles.value) { index, title ->
                        ShowCard(title, navController, addTitle = true)
                    }
                })
            CircularProgressBar(isDisplayed = loading)
        }
    }
}
