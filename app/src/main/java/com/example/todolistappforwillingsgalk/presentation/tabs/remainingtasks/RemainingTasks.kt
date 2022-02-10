package com.example.todolistappforwillingsgalk.presentation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistappforwillingsgalk.presentation.tabs.remainingtasks.AddTask

@Composable
fun RemainingTasks(
    onAdd: (String)->Unit
) {
    Column() {
        AddTask(onAdd = onAdd,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

    }

}