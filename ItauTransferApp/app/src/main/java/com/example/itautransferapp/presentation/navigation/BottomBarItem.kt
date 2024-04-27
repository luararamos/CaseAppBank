package com.example.itautransferapp.presentation.navigation

import com.example.itautransferapp.R

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val iconId: Int
) {
    object Home : BottomBarItem(
        route = "home",
        title = "Home",
        iconId = R.drawable.ic_home
    )

    object Search : BottomBarItem(
        route = "search",
        title = "Search",
        iconId = R.drawable.ic_search
    )

    object Messenger : BottomBarItem(
        route = "messenger",
        title = "Messenger",
        iconId = R.drawable.ic_messenger
    )
    object Settings : BottomBarItem(
        route = "settings",
        title = "Settings",
        iconId = R.drawable.ic_settings
    )


}