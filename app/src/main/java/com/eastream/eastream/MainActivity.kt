package com.eastream.eastream

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eastream.eastream.navigation.AppNavigation
import com.eastream.eastream.repository.StoreThemeMode
import com.eastream.eastream.ui.theme.EastreamTheme
import com.eastream.eastream.utils.AppTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

//var theme = mutableStateOf(true)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() {
    val context = LocalContext.current
    val dataStore = StoreThemeMode(context)

    val theme = dataStore.getTheme.collectAsState(initial = true)

    AppTheme(theme.value!!) {
        Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                AppNavigation()
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EastreamTheme {
        App()
    }
}
