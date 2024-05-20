package com.example.todoapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.todoapp.domain.CustomDrawerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    drawerState: CustomDrawerState,
    OnDrawerClick: (CustomDrawerState) -> Unit,
    darkTheme: Boolean,
    onThemeUpdate: () -> Unit
){
    
    
    Scaffold(
        modifier = modifier,
            //This make it that when the user clicks outside the navigation drawer it closes it
//            .clickable(enabled = drawerState == CustomDrawerState.Opened) {
//                OnDrawerClick(CustomDrawerState.Closed)

        topBar = {
            AppTopBar(
                drawerState = drawerState,
                onDrawerClicked = OnDrawerClick,
                darkTheme = darkTheme,
                onThemeUpdate = { onThemeUpdate() }
                )
        }
    ){
        Column {
            Text(text = "Helolasiodai dhwlkha")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        drawerState = CustomDrawerState.Closed,
        OnDrawerClick = {},
        darkTheme = false,
        onThemeUpdate = {}
        )

}