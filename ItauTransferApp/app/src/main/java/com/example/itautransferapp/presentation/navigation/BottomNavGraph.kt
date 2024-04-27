package com.example.itautransferapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itautransferapp.presentation.screens.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItem.Home.route
    ) {
        composable(route = BottomBarItem.Home.route) {
            HomeScreen(navController =navController)
        }
        composable(route = BottomBarItem.Search.route) {
        }
        composable(route = BottomBarItem.Messenger.route) {
        }
        composable(route = BottomBarItem.Settings.route) {
        }


    }
}