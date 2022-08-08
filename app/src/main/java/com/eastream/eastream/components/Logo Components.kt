package com.eastream.eastream.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//Logo Text
@Composable
fun EastreamLogoText(modifier: Modifier = Modifier) {
    Text(text = "Eastream",
        modifier = modifier.padding(start = 12.dp, bottom = 16.dp),
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground)
}

//SplashScreen Text
@Composable
fun SplashScreenText(modifier: Modifier = Modifier) {
    Text(text = "Eastream",
        modifier = modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.SemiBold
    )
}