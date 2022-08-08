@file:OptIn(ExperimentalFoundationApi::class)

package com.eastream.eastream.screens.titles

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eastream.eastream.components.*
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.navigation.MenuItem
import com.eastream.eastream.screens.search.SearchViewModel
import com.eastream.eastream.screens.search.SearchWidgetState
import com.eastream.eastream.screens.titleWords
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun TitlesScreen(navController: NavController,
                 viewModel: TitlesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                 searchViewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val networks = listOf("Netflix", "Rakuten Viki", "Hulu", "Amazon Prime Video", "The Roku Channel", "Tubi TV")
    var networkName = rememberSaveable { mutableStateOf(networks[0])}
    var listOfTitles = rememberSaveable {mutableStateOf(listOf<BasicTitleInfo>())}
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
                        viewModel.getTitles(networkName.value, listOfTitles)

                        itemsIndexed(listOfTitles.value) { index, title ->
                            ShowCard(title, navController, addTitle = true)
                        }
                    })
            CircularProgressBar(isDisplayed = loading)
        }
        }
    }
