package com.eastream.eastream.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.EastreamLogoText
import com.eastream.eastream.components.UserForm
import com.eastream.eastream.navigation.EastreamScreens

@ExperimentalComposeUiApi
@Preview
@Composable
fun LoginScreen(navController: NavController = NavController(context = LocalContext.current), viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val showLoginForm = rememberSaveable { mutableStateOf(true) }

    Surface(modifier = Modifier.fillMaxSize()) {
     Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
         EastreamLogoText(modifier = Modifier.padding(top = 90.dp, bottom = 16.dp))
         if (showLoginForm.value) UserForm(loading = false, isCreateAccount = false) { email, password ->
             //logins in user with FB auth account
             viewModel.signInWithEmailAndPassword(email, password) {
                 navController.navigate(EastreamScreens.UserProfileScreen.name)
             }
         }
         else {
             UserForm(loading = false, isCreateAccount = true) { email, password ->
                 //creates new FB auth account
                 viewModel.createUserWithEmailAndPassword(email, password) {
                     navController.navigate(EastreamScreens.UserProfileScreen.name)
                 }
             }
         }
         Row(
             modifier = Modifier.padding(15.dp),
             horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.CenterVertically
         ) {
             val text = if (showLoginForm.value) "Sign Up" else "Login"
             val userText = if (showLoginForm.value) "New User?" else "Already Registered?"
             Text(text = userText)
             Text(text, modifier = Modifier
                 .clickable {
                     showLoginForm.value = !showLoginForm.value
                 }
                 .padding(start = 5.dp),
                 fontWeight = FontWeight.Bold,
                 color = MaterialTheme.colors.secondaryVariant
             )
         }
     }
        }
    }





