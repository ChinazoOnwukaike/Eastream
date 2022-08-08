package com.eastream.eastream.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.navigation.EastreamScreens
import com.eastream.eastream.screens.userprofile.UserProfileViewModel

@Composable
fun ShowCard(title: BasicTitleInfo, navController: NavController, addTitle:Boolean, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .padding(4.dp)
        .width(100.dp)
        .height(if (addTitle) 220.dp else 150.dp)
        .clickable {
            navController.navigate(EastreamScreens.TitleInfoScreen.name + "/${title.id}")
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.surface) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
            horizontalAlignment = Alignment.Start) {
            Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original${title.poster}"), contentDescription = "${title.title} Poster",
                modifier = Modifier
                    .padding(if (addTitle) 5.dp else 0.dp)
                    .height(155.dp)
                    .fillMaxSize()
                , contentScale = if (addTitle) ContentScale.Fit else ContentScale.FillWidth)
            if (addTitle) {
                Column(modifier = Modifier.padding(top = 2.dp, start = 8.dp), verticalArrangement = Arrangement.Center) {
                    title.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption
                        )
                    }
                    title.year?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun NetworkCardsList(
    networkName: String,
    viewModel: UserProfileViewModel,
    listOfTitles: MutableState<List<BasicTitleInfo>>,
    navController: NavController,
) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.padding(12.dp),) {
        Text(text = "$networkName", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
        LazyRow(state = listState,
            content = {
                viewModel.getUserTitles(networkName, listOfTitles)
                itemsIndexed(listOfTitles.value) { index, title ->
                    ShowCard(title, navController, addTitle = false)}
            })
    }

}