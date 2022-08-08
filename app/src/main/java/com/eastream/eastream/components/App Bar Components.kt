package com.eastream.eastream.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eastream.eastream.navigation.EastreamScreens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
    homeBtn: Boolean = false,
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
                ) { Icon(Icons.Default.Menu, contentDescription = "Menu", tint = MaterialTheme.colors.onPrimary)
                }
                } else {
                    IconButton(
                        onClick = ({
                            navController.popBackStack()
                        })
                    ) { Icon(Icons.Outlined.ArrowBack, contentDescription = "Go Back", tint = MaterialTheme.colors.onPrimary) }
                }

                //DropMenu
                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    if (showNetworks) {
                        if (networkName != null && networks != null) {
                            DropMenu(networks, networkName)
                        }
                    }
                    else {
                        Text(text = header, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.onPrimary)
                    }
                }


                //Search
                if (showSearch) {
                    IconButton(onClick = {
                    //todo: Open Search Page
//                    Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                    onSearchTriggered()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search Button", tint = MaterialTheme.colors.onPrimary)
                }

                }//Back to Home
                else if (homeBtn) {
                    IconButton(onClick = {
                    navController.navigate(EastreamScreens.TitlesScreen.name)
                }) {
                    Icon(Icons.Outlined.Home, contentDescription = "Back to Home", tint = MaterialTheme.colors.onPrimary)
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
        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.onPrimary)) {
                        append("${networkName.value}")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 15.sp
                        )
                    ) {
                        append("▼")
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(Color.Transparent),
                color = MaterialTheme.colors.onPrimary
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