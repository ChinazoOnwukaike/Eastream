package com.eastream.eastream.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.AppBar
import com.eastream.eastream.components.DrawerHeader
import com.eastream.eastream.components.MenuDrawerBody
import com.eastream.eastream.components.ShowCard


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    searchQuery: String? = null
) {
    val scaffoldState = rememberScaffoldState()
    val gridState = rememberLazyListState(0)

    val listOfTitles by viewModel.listOfTitles
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                navController = navController,
                showMenu = false,
                showSearch = false,
                showNetworks = false,
                header = "Search Results",
                homeBtn = true
                )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            MenuDrawerBody(navController, scaffoldState)
        },
        drawerBackgroundColor = MaterialTheme.colors.background
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    state = gridState,
                    content = {
                        viewModel.getSearchResults(searchQuery.toString())
                         itemsIndexed(listOfTitles) { index, title ->
                                 ShowCard(title, navController, addTitle = true)
                          }

                    })
            if (listOfTitles.isEmpty()) {
                Text(text = "No Shows Found", modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.h4)
            }
            }
    }
}
