package com.eastream.eastream.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(isDisplayed: Boolean) {

    if(isDisplayed) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp), horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary
            )
    }
    }
}