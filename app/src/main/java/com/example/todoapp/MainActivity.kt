package com.example.todoapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.Screens.HomeScreen
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.viewmodels.HomeViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash Screen
        installSplashScreen().apply {

            setKeepOnScreenCondition {
                !homeViewModel.isReady.value
            }

            setOnExitAnimationListener {screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd {screen.remove()}

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd {screen.remove()}

                zoomX.start()
                zoomY.start()
            }
        }
        enableEdgeToEdge()

        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            TodoAppTheme(darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = ScreenA){
                        composable<ScreenA>{
                            HomeScreen(navController,darkTheme = darkTheme, onThemeUpdate = {darkTheme = !darkTheme})
                        }
                        composable<ScreenB>{

                        }
                    }
                }
            }
        }
    }
}


@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val id: Int,
    val title: String
)