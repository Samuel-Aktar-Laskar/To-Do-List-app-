package com.example.todolistappforwillingsgalk.presentation

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


private const val TAG = "ToDoAppStateLog"

enum class MainSections(
    val title: String,
    val route: String,
    val icon: ImageVector
){
    REMAINING_TASK("Remaining Tasks", "remainingtasks", Icons.Filled.Task),
    COMPLETED_TASK("Completed Tasks", "completedtasks", Icons.Filled.DoneAll)
}


@Composable
fun rememberToDoAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
)=remember(scaffoldState, navController, coroutineScope) {
    TradingAppState(scaffoldState, navController, coroutineScope)
}

class TradingAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope) {


    val bottomBarTabs = MainSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        Log.d(TAG, "navigateToBottomBarRoute: ")
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

}

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

