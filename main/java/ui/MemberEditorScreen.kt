package com.example.shiftscedule.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shiftscedule.MemberViewModel
import com.example.shiftscedule.data.Member

@Composable
fun MemberEditorScreen(
    onNavigateBack: () -> Unit,
    viewModel: MemberViewModel = viewModel()
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var access1UL by remember { mutableStateOf(false) }
    var access2UL by remember { mutableStateOf(false) }
    var access3UL by remember { mutableStateOf(false) }
    var access4UL by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Add New Member", style = MaterialTheme.typography.h6)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Workstation Access:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = access1UL, onCheckedChange = { access1UL = it })
            Text("1")
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(checked = access2UL, onCheckedChange = { access2UL = it })
            Text("2")
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(checked = access3UL, onCheckedChange = { access3UL = it })
            Text("3")
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(checked = access4UL, onCheckedChange = { access4UL = it })
            Text("4")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                if (name.isNotBlank()) {
                    val newMember = Member(
                        name = name,
                        access1 = access1UL,
                        access2 = access2UL,
                        access3 = access3UL,
                        access4 = access4UL
                    )
                    viewModel.addMember(newMember)
                    Toast.makeText(context, "Member added", Toast.LENGTH_SHORT).show()
                    onNavigateBack()
                } else {
                    Toast.makeText(context, "Name can't be empty", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }

            Button(onClick = onNavigateBack) {
                Text("Cancel")
            }
        }
    }
}
