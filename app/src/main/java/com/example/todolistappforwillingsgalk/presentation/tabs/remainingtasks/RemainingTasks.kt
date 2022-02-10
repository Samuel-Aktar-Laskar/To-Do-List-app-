package com.example.todolistappforwillingsgalk.presentation.tabs

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveDone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.presentation.components.RTaskCard
import com.example.todolistappforwillingsgalk.presentation.tabs.remainingtasks.AddTask
import com.example.todolistappforwillingsgalk.util.DataState


private const val TAG = "RemainingTasksTag"
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RemainingTasks(
    onAdd: (String)->Unit,
    tasks: LiveData<DataState<List<Task>>>,
    onClick: (Int,Boolean)->Unit,
    lifecycleOwner: LifecycleOwner,
    onDelete: (Int)->Unit,
    clearDoneTask: ()->Unit
) {
    var dTasks by remember {
        mutableStateOf(listOf<Task>())
    }

    tasks.observe(lifecycleOwner, Observer { dataState->
        when(dataState){
            is DataState.Success<List<Task>> -> {
                Log.d(TAG, "Search: result observed :${dataState.data}")
                dTasks = dataState.data.map {
                    it
                }

            }
            is DataState.Error -> {
                Log.d(TAG, "RemainingTasks: Error ${dataState.exception}")
            }
            is DataState.Loading -> {
                Log.d(TAG, "RemainingTasks: Loading")
            }
        }
    } )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { clearDoneTask() }) {
                Icon(imageVector = Icons.Default.RemoveDone, contentDescription = "Remove Done")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            AddTask(onAdd = onAdd,
                modifier = Modifier
            )
            Text(
                text = "My Tasks",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            LazyColumn( ){
                items(dTasks,{task:Task->task.id}){ task->
                    val dismissState = rememberDismissState()
                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        Log.d(TAG, "RemainingTasks: Dismissed")
                        onDelete(task.id)
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = 1.dp),
                        directions = setOf(
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                        },
                        background = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.White
                                    else -> Color.Red
                                }
                            )
                            val alignment = Alignment.CenterEnd
                            val icon = Icons.Default.Delete

                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        },
                        dismissContent = {
                            RTaskCard(
                                isDone = task.isDone,
                                task = task.task,
                                id = task.id,
                                onClick = onClick
                            )
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                        })
                }
            }


        }
    }





}