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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun TitlesScreen(navController: NavController, viewModel: TitlesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val networks = listOf("Netflix", "Viki", "Hulu", "Prime Video", "Roku", "Tubi")
    var networkName = rememberSaveable { mutableStateOf(networks[0])}
    var listOfTitles = rememberSaveable {mutableStateOf(listOf<BasicTitleInfo>())}
    val gridState = rememberLazyListState(0)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(viewModel, networks = networks, networkName = networkName, onNavigationIconClick = {
                scope.launch { scaffoldState.drawerState.open()
                }
            }, navController = navController)
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                modifier = Modifier.width(150.dp),
                items = listOf(
                MenuItem(
                    id = "profile",
                    title =  "Profile",
                    contentDescription =  "Go to profile",
                    icon = Icons.Outlined.Person
                ),
                MenuItem(
                    id = "settings",
                    title =  "Settings",
                    contentDescription =  "Settings",
                    icon = Icons.Outlined.Settings
                ),
                MenuItem(
                    id = "about",
                    title =  "About",
                    contentDescription =  "About Us",
                    icon = Icons.Outlined.Info
                ),
                MenuItem(
                    id = "logout",
                    title =  "Log Out",
                    contentDescription =  "Log Out",
                    icon = Icons.Outlined.Logout
                ),
            ), onItemClick = {
                when(it.id) {
                    "profile" -> navController.navigate(EastreamScreens.UserProfileScreen.name)
                    "settings" -> navController.navigate(EastreamScreens.SettingsScreen.name)
                    "about" -> navController.navigate(EastreamScreens.AboutScreen.name)
                    "logout" -> {
                        //todo: Log out user and then navigate back to Titles screen
                        FirebaseAuth.getInstance().signOut().run {
                            navController.navigate(EastreamScreens.TitlesScreen.name)
                        }
                        }
                            }
                        })},
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    state = gridState,
                    content = {
                        viewModel.getTitles(networkName.value, listOfTitles)

                        itemsIndexed(listOfTitles.value) { index, title ->
                            ShowCard(title)
                        }
                    })
        }
        }
    }


//@Preview
@Composable
fun AppBar(
    viewModel: TitlesViewModel,
    showSearch: Boolean = true,
    showNetworks: Boolean = true,
    showMenu: Boolean = true,
    navController: NavController,
    networks: List<String>,
    networkName: MutableState<String>,
    onNavigationIconClick: () -> Unit
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
                    onClick = onNavigationIconClick
                ) { Icon(Icons.Default.Menu, contentDescription = "Menu") }} else {
                    IconButton(
                        onClick = ({navController.navigate(EastreamScreens.TitlesScreen.name)})
                    ) { Icon(Icons.Outlined.ArrowBack, contentDescription = "Go Back") }
                }

                //DropMenu
                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    if (showNetworks) { DropMenu(viewModel, networks, networkName) }
                }


                //Search
                if (showSearch) {IconButton(onClick = {
                    //todo: Open Search Page
                    Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
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
fun DropMenu(viewModel: TitlesViewModel, networks: List<String>, networkName: MutableState<String>) {
    val context = LocalContext.current
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
                text = networkName.value, modifier = Modifier
//                .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(Color.Transparent)
            )
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
            }
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
fun ShowCard(title: BasicTitleInfo) {

    Card(modifier = Modifier
        .padding(4.dp)
        .width(100.dp)
        .height(220.dp)
        .clickable { /*onPressDetails.invoke(title.title.toString()) */ },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)), 
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.surface) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.Start) {
            Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${title.poster}"), contentDescription = "Poster Image",
            modifier = Modifier
                .padding(start = 5.dp, top = 5.dp, end = 5.dp)
                .height(155.dp)
                .fillMaxWidth()
                , contentScale = ContentScale.Fit)
            Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.Center) {
                title.title?.let {
                    Text(text = it,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption)
                }
                Text(text = "2022", modifier = Modifier
                    .fillMaxWidth(), style = MaterialTheme.typography.caption)
            }


        }
    }
}

@Composable
fun TitlesLazyColumn(showList: MutableList<HashMap <String, Any>>) {
LazyColumn { }
}