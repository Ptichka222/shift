package com.example.shiftscedule

import com.example.shiftscedule.ui.MemberEditorScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shiftscedule.ui.MemberListScreen
import com.example.shiftscedule.ui.ScheduleScreen
import com.example.shiftscedule.ui.ShiftScheduleScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "schedule") {
        composable("schedule") {
            ShiftScheduleScreen(navController = navController)
        }
        composable("members") {
            MemberListScreen(navController = navController)
        }
        composable("member_editor") {
            MemberEditorScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("add_member") {
            MemberEditorScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }
    }

}
