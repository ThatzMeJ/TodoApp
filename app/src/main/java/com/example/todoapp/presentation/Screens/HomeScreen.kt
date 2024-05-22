package com.example.todoapp.presentation.Screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.domain.CustomDrawerState
import com.example.todoapp.domain.NavigationItems
import com.example.todoapp.domain.isOpened
import com.example.todoapp.presentation.components.CustomDrawer.CustomDrawer
import com.example.todoapp.presentation.components.HomeContent
import kotlin.math.roundToInt


@SuppressLint("RememberReturnType", "UseOfNonLambdaOffsetOverload")
@Composable
fun HomeScreen(
    navController: NavController,
    darkTheme: Boolean,
    onThemeUpdate: () -> Unit
) {
    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed)}
    var selectedNavigationItems by remember { mutableStateOf(NavigationItems.HOME)}

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    // Handles if the user clicks the back button while the navigation drawer is active
    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ){

        CustomDrawer(
            selectedNavigationItems = selectedNavigationItems,
            onNavigationItemClick = {
                selectedNavigationItems = it
            },
            onCloseClick = { drawerState = CustomDrawerState.Closed}
        )

        HomeContent(
            //Add Modifier Parameter Here
            drawerState = drawerState,
            OnDrawerClick = {drawerState = it},
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale),
            darkTheme = darkTheme,
            onThemeUpdate = { onThemeUpdate() }
            )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), darkTheme = false, onThemeUpdate = {})
}