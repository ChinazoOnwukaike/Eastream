@file:OptIn(ExperimentalFoundationApi::class)

package com.eastream.eastream.screens.titles

import android.util.Log
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eastream.eastream.components.DrawerBody
import com.eastream.eastream.components.DrawerHeader
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.model.ETitle
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.navigation.MenuItem
import com.eastream.eastream.screens.SearchAppBar
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
                        }
                    )
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            MenuDrawerBody(navController)
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    state = gridState,
                    content = {
                        viewModel.getTitles(networkName.value, listOfTitles)

                        itemsIndexed(listOfTitles.value) { index, title ->
                            ShowCard(title, navController, addTitle = true)
                        }
                    })
        }
        }
    }

@Composable
fun MenuDrawerBody(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    DrawerBody(
        modifier = Modifier.width(150.dp),
        items = listOf(
            MenuItem(
                id = "home",
                title = "Home",
                contentDescription = "Go to homescreen",
                icon = Icons.Outlined.Home
            ),
            MenuItem(
                id = "profile",
                title = "Profile",
                contentDescription = "Go to profile",
                icon = Icons.Outlined.Person
            ),
            MenuItem(
                id = "settings",
                title = "Settings",
                contentDescription = "Settings",
                icon = Icons.Outlined.Settings
            ),
            MenuItem(
                id = "about",
                title = "About",
                contentDescription = "About Us",
                icon = Icons.Outlined.Info
            ),
            MenuItem(
                id = "logout",
                title = "Log Out",
                contentDescription = "Log Out",
                icon = Icons.Outlined.Logout
            ),
        ), onItemClick = {
            when (it.id) {
                "home" -> navController.navigate(EastreamScreens.TitlesScreen.name)
                "profile" -> {
                    if (auth.currentUser?.email.isNullOrEmpty()) {
                        navController.navigate(EastreamScreens.LoginScreen.name)
                    } else {
                    navController.navigate(EastreamScreens.UserProfileScreen.name)}
                }
                "settings" -> navController.navigate(EastreamScreens.SettingsScreen.name)
                "about" -> navController.navigate(EastreamScreens.AboutScreen.name)
                "logout" -> {
                    //todo: Log out user and then navigate back to Titles screen
                    FirebaseAuth.getInstance().signOut().run {
                        navController.navigate(EastreamScreens.TitlesScreen.name)
                    }
                }
            }
        })
}


//@Preview
@Composable
fun AppBar(
    showSearch: Boolean = true,
    showNetworks: Boolean = true,
    showMenu: Boolean = true,
    navController: NavController,
    networks: List<String>? = null,
    networkName: MutableState<String>? = null,
    onIconClick: () -> Unit = {},
    onSearchTriggered: () -> Unit = {},
    header: String = "",
    homeBtn: Boolean = false
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                //Menu
                if (showMenu) { IconButton(
                    onClick = onIconClick
                ) { Icon(Icons.Default.Menu, contentDescription = "Menu") }} else {
                    IconButton(
                        onClick = ({
                            navController.popBackStack()
                        })
                    ) { Icon(Icons.Outlined.ArrowBack, contentDescription = "Go Back") }
                }

                //DropMenu
                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    if (showNetworks) {
                        if (networkName != null && networks != null) {
                                DropMenu(networks, networkName)
                        }
                    }
                    else {
                        Text(text = header, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
                    }
                }


                //Search
                if (showSearch) {IconButton(onClick = {
                    //todo: Open Search Page
//                    Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                    onSearchTriggered()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search Button")
                }

            }//Back to Home
                else if (homeBtn) {IconButton(onClick = {
                    navController.navigate(EastreamScreens.TitlesScreen.name)
                }) {
                    Icon(Icons.Outlined.Home, contentDescription = "Back to Home")
                }
            }}

        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
//        navigationIcon = {
//            //todo ignore for now testing
//        },
        actions = {

        },
        elevation = 5.dp,
    )
}
//@Preview
@Composable
fun DropMenu(networks: List<String>, networkName: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .width(150.dp)
            .wrapContentSize(Alignment.TopStart),
            contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Text(
                text = "${networkName.value}    ▼", modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(Color.Transparent)
            )
    }
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {
            networks.forEachIndexed { index, network ->
                if (network != networkName.value) DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    networkName.value = networks[selectedIndex]
                }) {
                    Text(text = network)
                }

            }
        }
    }
}


val networkString = listOf("Netflix", "Viki", "Hulu")
//@Preview
@Composable
fun ShowCard(title: BasicTitleInfo, navController:NavController, addTitle:Boolean, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .padding(4.dp)
        .width(100.dp)
        .height(if (addTitle) 220.dp else 150.dp)
        .clickable {
            navController.navigate(EastreamScreens.TitleInfoScreen.name + "/${title.id}")
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)), 
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.surface) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.Start) {
            Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${title.poster}"), contentDescription = "${title.title} Poster",
            modifier = Modifier
                .padding(if (addTitle) 5.dp else 0.dp)
                .height(155.dp)
                .fillMaxWidth()
                , contentScale = if (addTitle) ContentScale.Fit else ContentScale.FillHeight)
            if (addTitle) {
            Column(modifier = Modifier.padding(top = 2.dp, start = 8.dp), verticalArrangement = Arrangement.Center) {
                title.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption
                    )
                }
                    title.year?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }

        }
    }
}
