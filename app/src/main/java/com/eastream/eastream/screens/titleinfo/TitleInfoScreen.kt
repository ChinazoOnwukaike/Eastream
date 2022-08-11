package com.eastream.eastream.screens.titleinfo

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eastream.eastream.components.*
import com.eastream.eastream.model.ETitle
import com.google.firebase.auth.FirebaseAuth


@ExperimentalFoundationApi
@Composable
fun TitleInfoScreen(navController: NavController = NavController(context = LocalContext.current),
                    viewModel: TitleInfoViewModel= androidx.lifecycle.viewmodel.compose.viewModel(),
                    titleId: String? = null) {
    val titleInfo = viewModel.titleInfo.value
    val scrollState = rememberScrollState()
    val loading = viewModel.loading.value

    Scaffold(
        topBar = {
            AppBar(navController = navController, showMenu = false, showNetworks = false, showSearch = false, onIconClick = {
                navController.popBackStack()
            }, header = "Show Details")
        }
    ) {
        
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxSize()) {
            Column(modifier = Modifier
                .padding(top = 12.dp)
                .verticalScroll(scrollState)
                ,verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
                ) {

                viewModel.getOneTitle(titleId)
                viewModel.checkInDb(titleId = titleId.toString())


                Text(text = "${titleInfo.title}", style = MaterialTheme.typography.h4, modifier = Modifier.padding(start = 16.dp), color = MaterialTheme.colors.onBackground)
                PosterRating(titleInfo, viewModel)
                titleInfo.networks?.let { titleInfo.networkImg?.let { it1 -> titleInfo.showLink?.let { it2 ->
                    WhereToWatch(modifier = Modifier
                        .padding(.12.dp)
                        .fillMaxWidth(.5f),it, it1, it2 )
                } } }
                Summary(titleInfo)
                Spacer(modifier = Modifier.padding(4.dp))
                Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${titleInfo.backdrop}"),
                    contentDescription = "${titleInfo.title} Backdrop Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(250.dp)
                )
            }
        }
    }
}


