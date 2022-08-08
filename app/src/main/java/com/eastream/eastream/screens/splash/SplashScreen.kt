package com.eastream.eastream.screens.splash

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eastream.eastream.R
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eastream.eastream.components.EastreamLogoText
import com.eastream.eastream.components.SplashScreenText
import com.eastream.eastream.navigation.EastreamScreens
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(navController: NavController = NavController(context = LocalContext.current)) {
    val scale = remember { Animatable(0f)}

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(EastreamScreens.TitlesScreen.name)
    }
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = MaterialTheme.colors.surface
        ) {
        Column(modifier = Modifier.scale(scale.value), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            val image: Painter = painterResource(id = R.drawable.triallogo)
            Image(painter = image,contentDescription = "Logo Image",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            modifier = Modifier.size(200.dp))
            SplashScreenText()

        }
        }
    }
