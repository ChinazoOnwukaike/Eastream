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
import com.eastream.eastream.model.ETitle
import com.eastream.eastream.screens.titles.AppBar
import com.google.firebase.auth.FirebaseAuth


//@Preview
@ExperimentalFoundationApi
@Composable
fun TitleInfoScreen(navController: NavController = NavController(context = LocalContext.current),
                    viewModel: TitleInfoViewModel= androidx.lifecycle.viewmodel.compose.viewModel(),
                    titleId: String? = null) {
    var titleInfo = remember {mutableStateOf(ETitle()) }
    var scrollState = rememberScrollState()
    val isLiked = remember {mutableStateOf(false)}

    Scaffold(
        topBar = {
            AppBar(navController = navController, showMenu = false, showNetworks = false, showSearch = false, onIconClick = {
                navController.popBackStack()
            }, details = true)
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

                val title = titleInfo.value
                viewModel.getOneTitle(titleId, titleInfo)
                viewModel.checkInDb(titleId = titleId.toString(), isLiked)


                Text(text = "${title.title}", style = MaterialTheme.typography.h4, modifier = Modifier.padding(start = 16.dp))
                PosterRating(titleInfo, viewModel, isLiked)
                title.networks?.let { title.networkImg?.let { it1 -> title.showLink?.let { it2 ->
                    WhereToWatch(modifier = Modifier
                        .padding(.12.dp)
                        .fillMaxWidth(.5f),it, it1, it2 )
                } } }
                Summary(title)
                Spacer(modifier = Modifier.padding(4.dp))
                Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${title.backdrop}"),
                    contentDescription = "${title.title} Backdrop Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(250.dp)
                )

            }
        }
    }
}

@Composable
private fun Summary(title: ETitle) {
    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.Start) {
        Text(text = "Summary", style = MaterialTheme.typography.h6)
        Text(text = "${title.summary}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), lineHeight = 25.sp)
    }
}


@ExperimentalFoundationApi
//@Preview
@Composable
fun PosterRating(titleInfo:MutableState<ETitle> = mutableStateOf(ETitle()), viewModel: TitleInfoViewModel, isLiked: MutableState<Boolean>) {
    val context = LocalContext.current
    val title = titleInfo.value
    val auth = FirebaseAuth.getInstance()

    Row(
        modifier = Modifier
            .padding(4.dp)
            .height(300.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${title.poster}"),
            contentDescription = "${title.title} Poster",
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(.5f)
                .fillMaxHeight()
                .background(color = Color.LightGray)
        )
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            IconButton(onClick = {
                if (auth.currentUser?.email.isNullOrEmpty()) {
                    Toast.makeText(context, "Please Sign In To Favorite", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (isLiked.value) {
                        viewModel.deleteTitle(title.id.toString())
                        Toast.makeText(
                            context,
                            "${title.title} Removed From Favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        isLiked.value = !isLiked.value
                    } else {
                        viewModel.addTitle(title.id.toString(), title.toMap() as Map<String, Any>)
                        Toast.makeText(
                            context,
                            "${title.title} Added To Favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        isLiked.value = !isLiked.value
                    }
                }
            }) {
                if (isLiked.value) {
                    Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "Removed From Favorites")
                }
                else {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Click To Add To Favorites")
                }
            }

            Text(text = "${title.voteAvg}/10 TMDB")
        }
    }
}
@ExperimentalFoundationApi
@Composable
fun WhereToWatch(modifier: Modifier = Modifier,
                 networks: List<String>,
                 networkImgs: HashMap<String, String>,
                 networkLinks: HashMap<String, String>) {
    val gridState = rememberLazyListState(0)
//    val adapter = if (networks.size <= 6) 60.dp else 130.dp

    Surface (modifier = modifier
        .fillMaxWidth()
        .background(color = Color.Blue)) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(60.dp),
            state = gridState,
            modifier = Modifier
                .padding(12.dp)
                .height(150.dp)
            ,content = {
                itemsIndexed(networks) { index, network ->
                    val networkImg = networkLinks[network].toString()
                    val link = networkImgs[network].toString()
                    NetworkTile(network, link, networkImg)
                }
            })
    }
}

//@Preview
@Composable
fun NetworkTile(network: String, link: String, networkImg: String) {
    val uriHandler = LocalUriHandler.current

    if (networkImg != null){
        Image(painter = rememberImagePainter(data = "https://image.tmdb.org/${networkImg}"),
            contentDescription = "$network Link",
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    uriHandler.openUri(link)
                }
                .width(40.dp)
                .height(60.dp)
                .border(BorderStroke(1.dp, color = Color.LightGray))
                .background(color = Color.LightGray),
            contentScale = ContentScale.FillHeight
        )}
    else {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.BrokenImage
                , contentDescription = "No Image")
        }
    }}

