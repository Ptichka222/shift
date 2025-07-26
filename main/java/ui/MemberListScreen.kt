package com.example.shiftscedule.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shiftscedule.MemberViewModel
import com.example.shiftscedule.data.Member
import androidx.navigation.NavController


@Composable
fun MemberListScreen(navController: NavController, viewModel: MemberViewModel = viewModel()) {
    val members by viewModel.members.collectAsState()
    var name by remember { mutableStateOf("") }
    var access1 by remember { mutableStateOf(false) }
    var access2 by remember { mutableStateOf(false) }
    var access3 by remember { mutableStateOf(false) }
    var access4 by remember { mutableStateOf(false) }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Member Name") }
        )

        Row {
            Checkbox(checked = access1, onCheckedChange = { access1 = it })
            Text("1UL", Modifier.padding(end = 8.dp))
            Checkbox(checked = access2, onCheckedChange = { access2 = it })
            Text("2UL", Modifier.padding(end = 8.dp))
            Checkbox(checked = access3, onCheckedChange = { access3 = it })
            Text("3UL", Modifier.padding(end = 8.dp))
            Checkbox(checked = access4, onCheckedChange = { access4 = it })
            Text("4")
        }

        Button(onClick = {
            viewModel.addMember(Member(
                name = name,
                access1 = access1,
                access2 = access2,
                access3 = access3,
                access4 = access4
            ))
            name = ""; access1 = false; access2 = false; access3 = false; access4 = false
        }) {
            Text("Add Member")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(members) { member ->
                Text(text = member.name)
            }
        }
    }
}
