package br.com.pucpr.homieworks.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = Modifier
    ) {
        myjobsNavGraph(navController)
        authNavGraph(navController)
        feedNavGraph(navController)
        mainNavGraph(navController)
    }
}
