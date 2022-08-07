package com.eastream.eastream.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eastream.eastream.navigation.MenuItem
import org.intellij.lang.annotations.JdkConstants

@Preview
@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 50.dp)) {
        EastreamLogoText()
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    Column () {
        LazyColumn (modifier) {
            items(items) {item ->
                Row (
                    modifier = Modifier
                        .clickable {
                            onItemClick(item)
                        }
                        .padding(16.dp)
                ){
                    Icon(imageVector = item.icon,
                        contentDescription = item.contentDescription)
                    Spacer(modifier = Modifier.width(26.dp))
                    Text(text = item.title,
                        style = itemTextStyle,
                        modifier = Modifier.weight(1f))
                }
            }
        }
//        Divider()
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically ) {
            Icon(imageVector = Icons.Outlined.DarkMode,
                contentDescription = "Toggle Screen Mode")
            Spacer(modifier = Modifier.width(26.dp))
            Text(text = "Night Mode",
                style = itemTextStyle,
                modifier = Modifier.weight(1f))
            ModeSwitch()
        }
    }

}