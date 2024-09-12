package com.jaehyeon.basic.deeplinkwithtype_safe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.jaehyeon.basic.deeplinkwithtype_safe.ui.theme.DeepLinkWithTypeSafeTheme
import kotlinx.serialization.Serializable

const val DEEP_LINK_DOMAIN = "morphine-coder.com"

@Serializable
data object HomeScreen

@Serializable
data class DeepLinkScreen(val id: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepLinkWithTypeSafeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = HomeScreen
                    ) {
                        composable<HomeScreen> {
                            HomeScreen()
                        }

                        composable<DeepLinkScreen>(
                            deepLinks = listOf(
                                navDeepLink<DeepLinkScreen>(
                                    basePath = "https://$DEEP_LINK_DOMAIN"
                                )
                            )
                        ) {
                            val id = it.toRoute<DeepLinkScreen>().id

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "The Id is $id")
                            }
                        }
                    }
                }
            }
        }
    }
}
