package com.example.todolistappforwillingsgalk.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistappforwillingsgalk.presentation.components.BottomBar
import com.example.todolistappforwillingsgalk.presentation.components.TopAppBar
import com.example.todolistappforwillingsgalk.presentation.tabs.RemainingTasks
import com.example.todolistappforwillingsgalk.presentation.ui.theme.ToDoListAppforWillingsGalkTheme

@Composable
fun ToDoApp(
    onAdd: (String) -> Unit
){
    ToDoListAppforWillingsGalkTheme {
        val appState = rememberToDoAppState()

        Scaffold(
            topBar = { TopAppBar() },
            bottomBar ={
                BottomBar(
                    navController = appState.navController,
                    tabs = appState.bottomBarTabs,
                    navigateToRoute = appState::navigateToBottomBarRoute
                )
            }
        ) {

            NavHost(
                appState.navController,
                startDestination = MainSections.REMAINING_TASK.route,
                Modifier.padding(it)
            ) {

                jetsnackNavGraph(
                    onAdd = onAdd
                )
            }

        }
    }
}

private fun NavGraphBuilder.jetsnackNavGraph(
onAdd: (String)->Unit
) {

    composable(MainSections.REMAINING_TASK.route) { from ->
       RemainingTasks(
           onAdd = onAdd
       )
        
    }
    composable(MainSections.COMPLETED_TASK.route) { from ->
      Text(text = "Completed task")
    }
  
}