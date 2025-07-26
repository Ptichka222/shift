package com.example.shiftscedule.ui

import com.example.shiftscedule.viewmodel.ShiftViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScheduleScreen(
    onNavigateToMembers: () -> Unit,
    viewModel: ShiftViewModel = viewModel()
) {
    val schedule = viewModel.schedule
    val totalWorked = viewModel.totalWorked
    val members by viewModel.members.collectAsState(emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModel.generateSchedule() }) {
                Text("Start")
            }
            Button(onClick = { onNavigateToMembers() }) {
                Text("Manage Members")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Table Header
        Row(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary)) {
            Text("Hour", Modifier.weight(1f).padding(4.dp), color = MaterialTheme.colorScheme.onPrimary)
            repeat(4) { i ->
                Text("WS${i + 1}", Modifier.weight(2f).padding(4.dp), color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        LazyColumn {
            items(24) { hour ->
                Row(Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(String.format("%02d", hour), Modifier.weight(1f).padding(4.dp))
                    repeat(4) { station ->
                        val member = schedule[hour]?.get(station)
                        Text(
                            member?.name ?: "-",
                            Modifier
                                .weight(2f)
                                .padding(4.dp)
                                .border(1.dp, MaterialTheme.colorScheme.outline)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Worked Hours", style = MaterialTheme.typography.titleMedium)
        Column {
            totalWorked.forEach { (name, hours) ->
                Text("$name: ${hours}h")
            }
        }
    }
}
