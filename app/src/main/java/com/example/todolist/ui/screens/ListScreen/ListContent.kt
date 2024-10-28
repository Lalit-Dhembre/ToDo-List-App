package com.example.todolist.ui.screens.ListScreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DismissValue.Default
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Tasks
import com.example.todolist.Navigation.Action
import com.example.todolist.Utils.RequestState
import com.example.todolist.Utils.SearchAppBarStates
import com.example.todolist.ui.Components.EmptyContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    paddingValues: PaddingValues,
    modifier: Modifier,
    onSwipetoDelete : (Action, Tasks) -> Unit,
    tasks:RequestState<List<Tasks>>,
    searchedTasks: RequestState<List<Tasks>>,
    searchAppBarStates: SearchAppBarStates,
    navigateToTaskScreen : (taskId: Int) -> Unit,
    sortState: RequestState<Priority>,
    lowPriorityTasks : List<Tasks>,
    highPriorityTasks : List<Tasks>
) {
    Log.d("LOW TASKS","$lowPriorityTasks")
    if(sortState is RequestState.Success) {
        when {
            searchAppBarStates == SearchAppBarStates.TRIGGERED -> {
                    if (searchedTasks is RequestState.Success) {
                        HandleListContent(
                            tasks = searchedTasks.data,
                            navigateToTaskScreen = navigateToTaskScreen,
                            paddingValues = paddingValues,
                            onSwipetoDelete
                        )
                    }
            }
            sortState.data == Priority.NONE -> {
                if (tasks is RequestState.Success) {
                    HandleListContent(
                        tasks = tasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        paddingValues = paddingValues,
                        onSwipetoDelete = onSwipetoDelete
                    )
                }
            }
            sortState.data == Priority.LOW -> {
                if(tasks is RequestState.Success){
                    Log.d("Sort Low","CLICKED")
                    HandleListContent(tasks = lowPriorityTasks, navigateToTaskScreen = navigateToTaskScreen, paddingValues = paddingValues, onSwipetoDelete)
                }
            }
            sortState.data == Priority.HIGH -> {
                if (tasks is RequestState.Success){
                    Log.d("SORT HIGH", "CLCIKED")
                    HandleListContent(tasks = highPriorityTasks, navigateToTaskScreen = navigateToTaskScreen, paddingValues = paddingValues, onSwipetoDelete)
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleListContent(
    tasks: List<Tasks>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    paddingValues: PaddingValues,
    onSwipetoDelete : (Action, Tasks) -> Unit,

    ){
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn(
            contentPadding = paddingValues
        ) {
            items(
                items = tasks,
                key = { task ->
                    task.id
                }
            ) { item: Tasks ->
                val dismissBoxState = rememberSwipeToDismissBoxState()
                val dismisDirection = dismissBoxState.dismissDirection
                val isDismissed = dismissBoxState.dismissDirection == SwipeToDismissBoxValue.EndToStart && dismissBoxState.progress == 1f
                if(isDismissed && dismisDirection == SwipeToDismissBoxValue.EndToStart){
                    val scope = rememberCoroutineScope()
                    SideEffect {
                        scope.launch {
                            onSwipetoDelete(Action.DELETE,item)
                        }
                    }
                }
                val degrees by animateFloatAsState(
                    if (dismissBoxState.progress in 0f..0.5f) 0f else -45f,
                    label = "Degree animation")

                var itemAppeared by remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = true) {
                    itemAppeared = true
                }
                AnimatedVisibility(visible = itemAppeared && !isDismissed,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 200
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 200
                        )
                    )
                ) {
                    SwipeToDismissBox(
                        state = dismissBoxState,
                        backgroundContent = { RedBackground(degrees = degrees)},
                        enableDismissFromEndToStart = true) {
                        TaskItem(
                            task = item,
                            navigateToTaskScreen = navigateToTaskScreen)
                    }
                }

            }
        }
    }
}



@Composable
fun RedBackground(degrees : Float){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)
        .padding(horizontal = 24.dp)){
        Icon(imageVector = Icons.Filled.Delete,
            contentDescription = "delete",
            tint = Color.White,
            modifier = Modifier.align(Alignment.CenterEnd))

    }
}

@Composable
fun TaskItem(
    task:Tasks,
    navigateToTaskScreen : (taskId: Int) -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreen(task.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier
                        .weight(9f),
                    text = task.title,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Canvas(
                    modifier = Modifier
                        .weight(1f),
                    contentDescription = "") {
                    drawCircle(
                        color = task.priority.color,
                        radius = 20f)
                }
            }
            Text(
                style = MaterialTheme.typography.labelSmall,
                text = task.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
fun TaskItemPreview(){
    var sampleTask : Tasks = Tasks(
        id = 1,
        title = "Task 1",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        priority = Priority.HIGH
    )

    TaskItem(task = sampleTask, navigateToTaskScreen = {})
}