package com.example.todolist.ui.screens.ListScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.todolist.Model.Priority
import com.example.todolist.Model.Tasks
import com.example.todolist.ui.Components.EmptyContent

@Composable
fun ListContent(
    paddingValues: PaddingValues,
    modifier: Modifier,

    tasks:List<Tasks>,
    navigateToTaskScreen : (taskId: Int) -> Unit
){
    if(tasks.isEmpty()){
        EmptyContent()
    }
    else {
        LazyColumn(
            contentPadding = paddingValues
        ) {
            items(
                items = tasks,
                key = { task ->
                    task.id
                }
            ) { item: Tasks ->
                TaskItem(
                    task = item,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
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