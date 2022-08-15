package com.eastream.eastream.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.eastream.eastream.model.ETitle
import com.eastream.eastream.screens.titleinfo.TitleInfoViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Summary(title: ETitle) {
    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.Start) {
        Text(text = "Summary", style = MaterialTheme.typography.h6)
        Text(text = "${title.summary}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), lineHeight = 25.sp)
    }
}


@ExperimentalFoundationApi
//@Preview
@Composable
fun PosterRating(title: ETitle = ETitle(), viewModel: TitleInfoViewModel) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val isLiked = viewModel.isLiked

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
                .background(color = Color.LightGray),
            contentScale = ContentScale.FillBounds
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
                    Toast.makeText(context, "Please Log In To Favorite", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (isLiked.value) {
                        viewModel.deleteTitle(title.id.toString())
                        Toast.makeText(
                            context,
                            "${title.title} Removed From Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.toggleLike()
                    } else {
                        viewModel.addTitle(title.id.toString(), title.toMap() as Map<String, Any>)
                        Toast.makeText(
                            context,
                            "${title.title} Added To Profile",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.toggleLike()
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
    val adapter = if (networks.size <= 6) 60.dp else 130.dp

    Surface (modifier = Modifier
        .fillMaxWidth()
        , contentColor = MaterialTheme.colors.onBackground)
    {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(60.dp),
            state = gridState,
            modifier = Modifier
                .padding(12.dp)
                .height(adapter)
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
            ,
            contentScale = ContentScale.FillHeight
        )
    }
    else {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.BrokenImage
                , contentDescription = "No Image")
        }
    }}
