package com.example.itautransferapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.navigation.BottomBarItem
import com.example.itautransferapp.presentation.navigation.BottomNavGraph
import com.example.itautransferapp.ui.theme.BIG_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_32
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.itautransferapp.ui.theme.FONT_8
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        it
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Search,
        BottomBarItem.Messenger,
        BottomBarItem.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(BIG_INPUT_HEIGHT)
            .fillMaxWidth()
            .shadow(elevation = ELEVATION_16, spotColor = Color.Black)
            .background(colorResource(id = R.color.colorTextTertiary)),
        contentColor = colorResource(id = R.color.colorTextTertiary),
        backgroundColor = Color.White
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    BottomNavigationItem(
        icon = {
            Box(
                modifier = Modifier
                    .padding(EXTRA_SMALL_PADDING)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(
                            MEDIUM_PADDING,
                            EXTRA_SMALL_PADDING,
                            MEDIUM_PADDING,
                            EXTRA_SMALL_PADDING
                        )
                        .graphicsLayer {
                            scaleX = if (isSelected) 1.8f else 1f
                            scaleY = if (isSelected) 1.8f else 1f
                        }
                        .then(
                            if (isSelected) Modifier
                                .background(
                                    color = colorResource(id = R.color.colorBackground),
                                    shape = RoundedCornerShape(
                                        topStart = CORNER_RADIUS_32,
                                        topEnd = CORNER_RADIUS_32,
                                        bottomEnd = CORNER_RADIUS_32,
                                        bottomStart = CORNER_RADIUS_32
                                    )
                                )
                            else Modifier
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .weight(0.8f)
                                .padding(EXTRA_SMALL_PADDING),
                            tint = if (isSelected) Color.White else colorResource(id = R.color.colorTextTertiary),
                            imageVector = ImageVector.vectorResource(id = screen.iconId),
                            contentDescription = "Navigation Icon"
                        )
                        if (isSelected) {
                            Text(
                                modifier = Modifier.weight(1f),
                                fontSize = FONT_8,
                                text = screen.title,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        },
        selectedContentColor = Color.Blue,
        selected = isSelected,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}