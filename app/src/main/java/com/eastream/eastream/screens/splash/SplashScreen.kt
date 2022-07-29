package com.eastream.eastream.screens.splash

import android.window.SplashScreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.EastreamLogoText

//@Preview
@Composable
fun SplashScreen(navController: NavController) {
    Text(text = "SplashScreen")
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp),
        shape = CircleShape,
        color= Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
        Logo()
        }
    }

@Composable
fun Logo () {
    Column(modifier = Modifier.padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        //Logo
        Surface (
            modifier = Modifier
                .padding(15.dp)
                .size(width = 150.dp, height = 80.dp),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            color= Color.LightGray,
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ){}
        //Name
        EastreamLogoText()
    }
}