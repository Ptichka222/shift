package com.example.shiftscedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.shiftscedule.ui.MemberListScreen
import com.example.shiftscedule.ui.theme.ShiftSceduleTheme
import com.example.shiftscedule.viewmodel.ShiftViewModel
import com.example.shiftscedule.ui.ScheduleScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ScheduleScreen(onNavigateToMembers = {
                    // TODO: handle navigation to member management screen
                })
            }
        }


    }
}
