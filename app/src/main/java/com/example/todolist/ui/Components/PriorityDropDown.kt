package com.example.todolist.ui.Components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.Model.Priority
import com.example.todolist.ui.Viewmodel.SharedViewmodel

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    sharedViewmodel: SharedViewmodel
) {
    // Animate rotation of the dropdown arrow based on expanded state
    val isExpanded by sharedViewmodel.expanded.collectAsState()

    val rotation: Float by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(60.dp)
            .clickable { sharedViewmodel.toggleDropdown() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                shape = RoundedCornerShape(10f)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(
            modifier = Modifier
                .weight(1f),
            contentDescription = "priority"
        ) {
            drawCircle(
                color = priority.color,
                radius = 20f
            )
        }

        Text(
            text = priority.name,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(8f),
            color = Color.DarkGray
        )

        IconButton(onClick = { sharedViewmodel.toggleDropdown() }) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Drop Down",
                modifier = Modifier
                    .rotate(rotation)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = LinearOutSlowInEasing
                        )
                    )
            )
        }

        // Dropdown Menu
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { sharedViewmodel.toggleDropdown() }
        ) {
            DropItem(Priority.LOW, onPrioritySelected, sharedViewmodel)
            DropItem(Priority.MEDIUM, onPrioritySelected, sharedViewmodel)
            DropItem(Priority.HIGH, onPrioritySelected, sharedViewmodel)
            DropItem(Priority.NONE, onPrioritySelected, sharedViewmodel)
        }
    }
}

@Composable
fun DropItem(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    sharedViewmodel: SharedViewmodel
) {
    DropdownMenuItem(
        text = {
            Row {
                Text(
                    text = priority.name,
                    modifier = Modifier.weight(3f)
                )
                Canvas(
                    modifier = Modifier
                        .size(20.dp)
                        .weight(1f),
                    contentDescription = "priority"
                ) {
                    drawCircle(
                        color = priority.color
                    )
                }
            }
        },
        onClick = {
            onPrioritySelected(priority)
            sharedViewmodel.toggleDropdown()
        }
    )
}
