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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.BottomBarItem
import com.example.itautransferapp.presentation.components.BottomNavGraph

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
            .height(80.dp)
            .fillMaxWidth()
            .shadow(elevation = 16.dp, spotColor = Color.Black)
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
                    .padding(4.dp, 4.dp,4.dp,4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(16.dp, 4.dp, 16.dp, 4.dp)
                        .graphicsLayer {
                            scaleX = if (isSelected) 1.8f else 1f
                            scaleY = if (isSelected) 1.8f else 1f
                        }
                        .then(
                            if (isSelected) Modifier
                                .background(
                                    color = colorResource(id = R.color.colorBackground),
                                    shape = RoundedCornerShape(
                                        topStart = 30.dp,
                                        topEnd = 30.dp,
                                        bottomEnd = 30.dp,
                                        bottomStart = 30.dp
                                    )
                                )
                            else Modifier
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .weight(0.8f)
                                .padding(4.dp, 4.dp, 4.dp, 4.dp),
                            tint = if (isSelected) Color.White else colorResource(id = R.color.colorTextTertiary),
                            imageVector = ImageVector.vectorResource(id = screen.iconId),
                            contentDescription = "Navigation Icon"
                        )
                        if (isSelected) {
                            Text(
                                modifier = Modifier.weight(1f),
                                fontSize = 8.sp,
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