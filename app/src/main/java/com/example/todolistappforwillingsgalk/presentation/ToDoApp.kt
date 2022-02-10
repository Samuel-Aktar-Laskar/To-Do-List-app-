package com.example.todolistappforwillingsgalk.presentation

import android.app.UiAutomation
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.LiveLiteralFileInfo
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.presentation.components.BottomBar
import com.example.todolistappforwillingsgalk.presentation.components.TopAppBar
import com.example.todolistappforwillingsgalk.presentation.tabs.CompletedTask
import com.example.todolistappforwillingsgalk.presentation.tabs.RemainingTasks
import com.example.todolistappforwillingsgalk.presentation.ui.theme.ToDoListAppforWillingsGalkTheme
import com.example.todolistappforwillingsgalk.util.DataState

@Composable
fun ToDoApp(
    onAdd: (String) -> Unit,
    onClick: (Int, Boolean)->Unit,
    lifecycleOwner: LifecycleOwner,
    tasks: LiveData<DataState<List<Task>>>,
    completedTasks:LiveData<DataState<List<Task>>>,
    onDelete: (Int, Boolean)->Unit,
    clearDoneTask: ()->Unit
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
                    onAdd = onAdd,
                    tasks =  tasks,
                    lifecycleOwner = lifecycleOwner,
                    onClick = onClick,
                    onDelete = onDelete,
                    completedTasks = completedTasks,
                    clearDoneTask = clearDoneTask
                )
            }

        }
    }
}

private fun NavGraphBuilder.jetsnackNavGraph(
onAdd: (String)->Unit,
onClick: (Int,Boolean)->Unit,
lifecycleOwner: LifecycleOwner,
tasks: LiveData<DataState<List<Task>>>,
completedTasks:LiveData<DataState<List<Task>>>,
onDelete: (Int,Boolean)->Unit,
clearDoneTask: ()->Unit
) {

    composable(MainSections.REMAINING_TASK.route) { from ->
       RemainingTasks(
           onAdd = onAdd,
           onClick = onClick,
           lifecycleOwner = lifecycleOwner,
           tasks = tasks,
           onDelete = {
               onDelete(it, true) },
           clearDoneTask = clearDoneTask
       )
        
    }
    composable(MainSections.COMPLETED_TASK.route) { from ->
      CompletedTask(
          tasks = completedTasks,
          lifecycleOwner = lifecycleOwner,
          onDelete = {
              onDelete(it, false) }
      )
    }
  
}