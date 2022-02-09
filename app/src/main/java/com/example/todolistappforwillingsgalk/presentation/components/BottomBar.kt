package com.example.todolistappforwillingsgalk.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todolistappforwillingsgalk.presentation.MainSections

@Composable
fun BottomBar(
    navController : NavController,
    tabs: Array<MainSections>,
    navigateToRoute: (String) -> Unit
){
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        tabs.forEach { homeSection ->
            BottomNavigationItem(
                icon = {
                    Icon(homeSection.icon, contentDescription = null)
                },
                label = { Text(text = homeSection.title) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == homeSection.route
                } ?: false,
                onClick = {
                    navigateToRoute(homeSection.route)
                }
            )
        }



    }
}