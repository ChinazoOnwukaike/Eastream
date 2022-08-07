package com.eastream.eastream.screens.search

import android.app.appsearch.SearchResult
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.DrawerHeader
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.titleWords
import com.eastream.eastream.screens.titles.AppBar
import com.eastream.eastream.screens.titles.MenuDrawerBody
import com.eastream.eastream.screens.titles.ShowCard
import kotlinx.coroutines.launch


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
