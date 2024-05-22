package com.example.todoapp.presentation.components.CustomDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.domain.NavigationItems


@Composable
fun CustomDrawer(
    selectedNavigationItems: NavigationItems?,
    onNavigationItemClick: (NavigationItems) -> Unit,
    onCloseClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.6f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Back Arrow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ){
            IconButton(onClick = { onCloseClick() }) {

                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Back Arrow Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "App Icon"
        )
        Spacer(modifier = Modifier.height(40.dp))
        //Navigation Items
        //Still want to find out why the toTypedArray() doesn't work with value()?
        NavigationItems.entries.toTypedArray().take(2).forEach { navigationItem ->
            NavigationItemsView(
                navigationItems = navigationItem,
                selected = navigationItem == selectedNavigationItems,
                onClick = { onNavigationItemClick(navigationItem)}
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.weight(1f))

        NavigationItems.entries.toTypedArray().takeLast(1).forEach { navigationItem ->
            NavigationItemsView(
                navigationItems = navigationItem,
                selected = navigationItem == selectedNavigationItems,
                onClick = {
                    when (navigationItem) {
                        NavigationItems.SETTING -> {
                            onNavigationItemClick(NavigationItems.SETTING)
                        }
                        else -> {}
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun NavigationDrawerPreview() {
    CustomDrawer(
        selectedNavigationItems = null,
        onNavigationItemClick = {},
        onCloseClick = {}
    )
}