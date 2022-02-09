package com.example.todolistappforwillingsgalk.presentation.tabs.remainingtasks

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.todolistappforwillingsgalk.presentation.ui.theme.ToDoListAppforWillingsGalkTheme




@Composable
fun AddTask(
    onAdd: (String) -> Unit,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState()
) {
    Surface(modifier = modifier) {
        Column {
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Search Bar
                SearchBar(
                    modifier = Modifier.weight(1f),
                    query = state.query,
                    onQueryChange = { state.query = it },
                    state = state
                ) {
                    onAdd(state.query.text)
                    state.query = TextFieldValue("")
                }
                val focusManager = LocalFocusManager.current

                //Add Button
                IconButton(
                    onClick = {
                        validateTask(state = state, focusManager = focusManager)
                        onAdd(state.query.text)
                        state.query = TextFieldValue("")

                    },

                    ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircleOutline,
                        contentDescription = "Add Task",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            if (state.error) {
                Error()
            }

            LaunchedEffect(state.query.text) {
                if (state.query.text.length >= 5 || state.query.text.isEmpty()) {
                    state.error = false
                }
            }

        }
    }
}


@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    error: Boolean = false

): SearchState {
    return remember {
        SearchState(
            query = query,
            error = error
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    error: Boolean
) {
    var query by mutableStateOf(query)
    var error by mutableStateOf(error)
}


@Composable
private fun SearchBar(
    query: TextFieldValue,
    state: SearchState,
    onQueryChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onDone: () -> Unit
) {
    Surface(
        color = Color.White,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .wrapContentHeight()
            .padding(top = 8.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.BottomStart
            ) {
                if (query.text.isEmpty()) {
                    SearchHint()
                }
                val focusManager = LocalFocusManager.current

                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 17.sp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                            val b = validateTask(state = state, focusManager = focusManager)
                            if (b) onDone()
                            else return@KeyboardActions
                        }
                    ),
                )
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 1.dp)
            )

        }

    }
}

fun validateTask(state: SearchState, focusManager: FocusManager): Boolean {

    if (state.query.text.length < 5) {
        state.error = true
        return false
    }
    focusManager.clearFocus()
    return true
}


@Composable
fun Error() {
    Text(
        text = "Please write a valid task",
        color = Color.Red
    )
}


@Composable
private fun SearchHint() {
    Text(
        text = "Write Task",
        color = Color.Gray,
        fontSize = 17.sp
    )
}


@Preview
@Composable
fun PreviewSearchbar() {
    ToDoListAppforWillingsGalkTheme() {
        SearchBar(
            query = TextFieldValue(),
            onQueryChange = {},
            state = SearchState(TextFieldValue(), false)
        ) {

        }
    }
}
