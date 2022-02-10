package com.example.todolistappforwillingsgalk.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistappforwillingsgalk.presentation.ui.theme.CardBlue

@Composable
fun CTaskCard(
    task: String
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = CardBlue
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(selected = true,
                onClick = {
                },
                modifier = Modifier.padding(horizontal = 10.dp).scale(0.75f),
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.White
                )
            )

            Text(
                text = task,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
                color =  Color.White,
            )


        }
    }


}