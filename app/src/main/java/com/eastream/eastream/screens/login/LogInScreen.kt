package com.eastream.eastream.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.R
import com.eastream.eastream.components.EastreamLogoText
import com.eastream.eastream.components.EmailInput
import com.eastream.eastream.components.PasswordInput
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.UserProfileScreen

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
                 navController.navigate(EastreamScreens.TitlesScreen.name)
             }
         }
         else {
             UserForm(loading = false, isCreateAccount = true) { email, password ->
                 //creates new FB auth account
                 viewModel.createUserWithEmailAndPassword(email, password) {
                     navController.navigate(EastreamScreens.TitlesScreen.name)
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


@ExperimentalComposeUiApi
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = {email, pwd ->}
) {
    val email = rememberSaveable{ mutableStateOf("") }
    val password = rememberSaveable{ mutableStateOf("") }
    val passwordVisibility = rememberSaveable{ mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(emailState = email, enabled = !loading, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        })
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
    })
        //Password Info String
        if (isCreateAccount) Text(text = stringResource(id = R.string.create_acct),
            modifier = Modifier.padding(bottom = 2.dp), style = MaterialTheme.typography.subtitle2
        ) else Text(text = "")

        if (isCreateAccount) Spacer(modifier = Modifier.height(5.dp))

        SubmitButton (
            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid
                ) {
                onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
}}

@Composable
fun SubmitButton(textId: String, loading: Boolean, validInputs: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick,
    modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
    enabled = !loading && validInputs,
    shape = CircleShape
        ){
            if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
            else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}



