package com.example.todoapp.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItems(
    val title: String,
    val icon: ImageVector
) {
    HOME(
       title = "Home",
       icon = Icons.Filled.Home
    ),
    PROFILE(
        title = "Profile",
        icon =Icons.Filled.Person
    ),
    SETTING(
        title = "Settings",
        icon =Icons.Filled.Settings
    )
}