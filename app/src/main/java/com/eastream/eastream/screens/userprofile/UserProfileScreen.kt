package com.eastream.eastream.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.DrawerHeader
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.search.SearchViewModel
import com.eastream.eastream.screens.search.SearchWidgetState
import com.eastream.eastream.screens.titles.AppBar
import com.eastream.eastream.screens.titles.MenuDrawerBody
import com.eastream.eastream.screens.titles.ShowCard
import com.eastream.eastream.screens.userprofile.UserProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
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
        val networks = listOf("Netflix", "Rakuten Viki", "Hulu", "Amazon Prime Video", "The Roku Channel", "Tubi TV")
        val scrollState = rememberScrollState()
        val user = FirebaseAuth.getInstance().currentUser
        val searchWidgetState by searchViewModel.searchWidgetState
        val searchTextState by searchViewModel.searchTextState
        val context = LocalContext.current

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
        ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                        Column(modifier = Modifier.verticalScroll(scrollState)) {
//                                Row(modifier = Modifier.clickable { navController.navigate(EastreamScreens.TitlesScreen.name)  }) {
//                                        IconButton(onClick = { navController.navigate(EastreamScreens.TitlesScreen.name) }) {
//                                                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back to Homescreen")
//                                        }
//                                        Text(text = "Back To Home", modifier = Modifier.padding(10.dp), style = MaterialTheme.typography.h6)
//                                }

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

@Composable
private fun NetworkCardsList(
        networkName: String,
        viewModel: UserProfileViewModel,
        listOfTitles: MutableState<List<BasicTitleInfo>>,
        navController: NavController,
) {
        val listState = rememberLazyListState()
        Column(modifier = Modifier.padding(12.dp),) {
                Text(text = "$networkName", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
                LazyRow(state = listState,
                        content = {
                                viewModel.getUserTitles(networkName, listOfTitles)
                                itemsIndexed(listOfTitles.value) { index, title ->
                                        ShowCard(title, navController, addTitle = false)}
                        })
        }

}

@Composable
fun SearchAppBar(
        text: String,
        onTextChange: (String) -> Unit,
        onCloseClicked: () -> Unit,
        onSearchClicked: (String) -> Unit,
) {
        Surface(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                elevation = AppBarDefaults.TopAppBarElevation,
                color = MaterialTheme.colors.primary
        ) {
                TextField(
                        modifier = Modifier
                                .fillMaxWidth(),
                        value = text,
                        onValueChange = {
                                onTextChange(it)
                        },
                        placeholder = {
                                Text(modifier = Modifier
                                        .alpha(ContentAlpha.medium),
                                        text = "Search here ...",
                                        color = Color.White
                                )
                        },
                        textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize
                        ),
                        singleLine = true,
                        leadingIcon = {
                                IconButton(modifier = Modifier.alpha(ContentAlpha.medium),
                                        onClick = { /*TODO*/ }) {
                                        Icon(
                                                imageVector = Icons.Outlined.Search,
                                                contentDescription = "Search Icon",
                                                tint = Color.White
                                        )
                                }
                        },
                        trailingIcon = {
                                IconButton(onClick = {
                                        if (text.isNotEmpty()){
                                                onTextChange("")
                                        } else {
                                                onCloseClicked()
                                        }
                                }) {
                                        Icon(
                                                imageVector = Icons.Outlined.Close,
                                                contentDescription = "Close Icon",
                                                tint = Color.White
                                        )
                                }
                        },
                        keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions ( onSearch = {
                                onSearchClicked(text)
                        }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
                        )
                )
        }
}

@Preview
@Composable
fun SearchAppBarPreview() {
        SearchAppBar(
                text = "Some Random Text",
                onTextChange = {},
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {}
        )
}

//create true title casing
fun String.titleWords(): String = split(" ").map { it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
        ) else it.toString()
} }.joinToString(" ")

