package com.example.todolistappforwillingsgalk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshRemainingTasks()
        viewModel.refreshCompletedTasks()
        setContent {
            ToDoApp(
                onAdd = {
                    viewModel.addTask(it)
                },
                onClick = { id, isDone ->
                    if (isDone)
                    viewModel.TaskUndone(id)
                    else
                        viewModel.TaskDone(id)
                },
                lifecycleOwner = this,
                tasks = viewModel.remainingTasks,
                onDelete = {id, isR->
                    viewModel.delete(id, isR)
                },
                completedTasks = viewModel.completedTasks,
                clearDoneTask = {
                    viewModel.ClearCompletedTasks()
                }
            )

        }
    }
}
