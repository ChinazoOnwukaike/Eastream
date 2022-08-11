package com.eastream.eastream.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eastream.eastream.R
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.model.MenuItem
import com.eastream.eastream.repository.StoreThemeMode
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Preview
@Composable
fun DrawerHeader() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 50.dp)) {
        val image: Painter = painterResource(id = R.drawable.triallogo)

        Image(painter = image,contentDescription = "Logo Image",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            modifier = Modifier.size(100.dp).padding(start=16.dp))
        EastreamLogoText()
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    Column () {
        LazyColumn (modifier) {
            items(items) {item ->
                Row (
                    modifier = Modifier
                        .clickable {
                            onItemClick(item)
                        }
                        .padding(16.dp)
                ){
                    Icon(imageVector = item.icon,
                        contentDescription = item.contentDescription)
                    Spacer(modifier = Modifier.width(26.dp))
                    Text(text = item.title,
                        style = itemTextStyle,
                        modifier = Modifier.weight(1f))
                }
            }
        }
        Divider(modifier=Modifier.padding(4.dp), startIndent = 8.dp, thickness = .5.dp, color = MaterialTheme.colors.onBackground)
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically ) {
            Icon(imageVector = Icons.Outlined.ModeNight,
                contentDescription = "Toggle Screen Mode")
            Spacer(modifier = Modifier.width(26.dp))
            Text(text = "Night Mode",
                style = itemTextStyle,
                modifier = Modifier.weight(1f))
            ModeSwitch()
        }
    }

}

@Composable
fun MenuDrawerBody(navController: NavController, scaffoldState:ScaffoldState) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
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
                id = "about",
                title = "About",
                contentDescription = "About Us",
                icon = Icons.Outlined.Info
            ),

            MenuItem(
                id = if (auth.currentUser?.email.isNullOrEmpty()) "login" else "logout",
                title = if (auth.currentUser?.email.isNullOrEmpty()) "Log In" else "Log Out",
                contentDescription = if (auth.currentUser?.email.isNullOrEmpty()) "Log In" else "Log Out",
                icon = if (auth.currentUser?.email.isNullOrEmpty()) Icons.Outlined.Logout else Icons.Outlined.Login
            ),
        ), onItemClick = {
            when (it.id) {
                "home" -> {
                    navController.navigate(EastreamScreens.TitlesScreen.name)
                }
                "profile" -> {
                    if (auth.currentUser?.email.isNullOrEmpty()) {
                        navController.navigate(EastreamScreens.LoginScreen.name)

                    } else {
                        navController.navigate(EastreamScreens.UserProfileScreen.name)
                    }

                }
                "about" -> {
                    navController.navigate(EastreamScreens.AboutScreen.name)
                }
                "logout" -> {
                    //todo: Log out user and then navigate back to Titles screen
                        auth.signOut().run {
                        navController.navigate(EastreamScreens.TitlesScreen.name)
                    }
                    Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
                }
                "login" -> {
                    navController.navigate(EastreamScreens.LoginScreen.name)
                }
            }
        })
}

//Switch Bar
@Composable
fun ModeSwitch() {
    val context = LocalContext.current
    val dataStore = StoreThemeMode(context)
    val scope = rememberCoroutineScope()
    val theme = dataStore.getTheme.collectAsState(initial = true)
    val checkedState = dataStore.getSwitch.collectAsState(initial = true)

    Switch(checked = checkedState.value!!,
        onCheckedChange = {
            scope.launch {
                dataStore.toggleSwitch(!checkedState.value!!)
                dataStore.toggleTheme(!theme.value!!)
            }
        }
    )

}