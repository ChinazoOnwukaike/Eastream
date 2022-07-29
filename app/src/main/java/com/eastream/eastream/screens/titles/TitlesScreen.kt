package com.eastream.eastream.screens.titles

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.DrawerBody
import com.eastream.eastream.components.DrawerHeader
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.navigation.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun TitlesScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(onNavigationIconClick = {
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
            //todo: home content
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "TV Show Content")
            }
        }
    }
}

//@Preview
@Composable
fun AppBar(
    showSearch: Boolean = true,
    showNetworks: Boolean = true,
    showMenu: Boolean = true,
    navController: NavController,
    onNavigationIconClick: () -> Unit,
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (showMenu) { IconButton(
                    onClick = onNavigationIconClick
                ) { Icon(Icons.Default.Menu, contentDescription = "Menu") }} else {
                    IconButton(
                        onClick = ({navController.navigate(EastreamScreens.TitlesScreen.name)})
                    ) { Icon(Icons.Outlined.ArrowBack, contentDescription = "Go Back") }
                }

                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    if (showNetworks) { DropMenu() }
                }



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
@Preview
@Composable
fun DropMenu() {
    val context = LocalContext.current
    val networks = listOf("Netflix", "Viki", "Hulu", "Prime Video", "Roku", "Tubi")
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var networkName by remember { mutableStateOf("Netflix") }
    Box(
        modifier = Modifier
            .width(150.dp)
            .wrapContentSize(Alignment.TopStart),
            contentAlignment = Alignment.CenterStart
    ) {        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        Text(
            text = networkName, modifier = Modifier
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
                if (network != networkName) DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = network)
                    networkName = networks[selectedIndex]
                    // todo: query shows for networkName
                }

            }
        }
    }
}