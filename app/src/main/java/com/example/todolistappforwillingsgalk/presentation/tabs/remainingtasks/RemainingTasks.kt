package com.example.todolistappforwillingsgalk.presentation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistappforwillingsgalk.presentation.tabs.remainingtasks.AddTask

@Composable
fun RemainingTasks() {
    Column() {
        AddTask(onAdd = {
                        //add to room database
        },
            modifier = Modifier.padding(horizontal = 30.dp)
        )
    }

}