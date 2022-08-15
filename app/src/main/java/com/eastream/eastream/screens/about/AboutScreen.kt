package com.eastream.eastream.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.eastream.eastream.R

@Composable
fun AboutScreen(navController: NavController) {
    val tmdbSvgLink = "https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_1-5bdc75aaebeb75dc7ae79426ddd9be3b2be1e342510f8202baf6bffa71d7f5c4.svg"
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Eastream is the premiere app for finding where Korean Content is streaming.",
            style = MaterialTheme.typography.h6, modifier = Modifier.padding(4.dp), textAlign = TextAlign.Center)

        Text(text = "App is built in part with TMDB and JustWatch",
            style = MaterialTheme.typography.h6, modifier = Modifier.padding(4.dp), textAlign = TextAlign.Center)
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val painter = rememberImagePainter(tmdbSvgLink)
            Image(
                painter = painter,
                contentDescription = "TMDB SVG Image",
                modifier = Modifier
                    .size(80.dp).padding(12.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

