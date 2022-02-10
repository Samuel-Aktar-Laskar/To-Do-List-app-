package com.example.todolistappforwillingsgalk.presentation.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistappforwillingsgalk.presentation.ui.theme.CardBlue
import com.example.todolistappforwillingsgalk.presentation.ui.theme.CardGray
import com.example.todolistappforwillingsgalk.presentation.ui.theme.CardRed
import com.example.todolistappforwillingsgalk.presentation.ui.theme.LightBlue

@Composable
fun RTaskCard(
    isDone: Boolean,
    task: String,
    id: Int,
    onClick:(id: Int,isDone: Boolean)->Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (isDone) CardGray else CardBlue
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(id,isDone)
                }
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(selected = isDone,
                onClick = {
                    onClick(id,isDone)
                },
                modifier = Modifier.padding(horizontal = 10.dp).scale(0.75f),
                colors = RadioButtonDefaults.colors(
                    selectedColor = CardRed,
                    unselectedColor = Color.White
                )
            )

            Text(
                text = task,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = if (isDone) CardRed else Color.White,
                style = TextStyle(textDecoration =  if(isDone) TextDecoration.LineThrough else TextDecoration.None)
            )


        }
    }

    
}