package com.example.shiftscedule.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiftscedule.data.AppDatabase
import com.example.shiftscedule.data.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.example.shiftscedule.data.ShiftEntry

class ShiftViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).memberDao()

    private val _members = MutableStateFlow<List<Member>>(emptyList())
    val members: StateFlow<List<Member>> = _members

    // Schedule: hour (0–23) → map of station → member
    val schedule = mutableMapOf<Int, MutableMap<Int, Member>>()

    // Track hours worked per member
    val totalWorked = mutableMapOf<String, Int>()
    val shiftSchedule = MutableStateFlow<List<ShiftEntry>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getAll().collect {
                _members.value = it
            }
        }
    }

    fun generateSchedule() {
        schedule.clear()
        totalWorked.clear()

        // Build availability matrix: [member] → [hours they can work]
        val worked = mutableMapOf<Member, MutableList<Int>>()

        val allMembers = _members.value.shuffled() // shuffle to distribute work

        // Initialize worked map
        allMembers.forEach { worked[it] = mutableListOf() }

        for (hour in 0 until 24) {
            schedule[hour] = mutableMapOf()

            for (station in 0 until 4) {
                val eligible = allMembers.filter { member ->
                    // Permission to work at station?
                    val allowed = when (station) {
                        0 -> member.access1
                        1 -> member.access2
                        2 -> member.access3
                        3 -> member.access4
                        else -> false
                    }

                    if (!allowed) return@filter false

                    val hoursWorked = worked[member] ?: return@filter false
                    if (hour in hoursWorked) return@filter false

                    // Check previous 2 hours
                    val last = hoursWorked.takeLast(2)
                    if (last.size == 2 && last[1] == hour - 1 && last[0] == hour - 2) {
                        return@filter false // needs 1-hour rest
                    }

                    true
                }

                val assigned = eligible.firstOrNull()
                if (assigned != null) {
                    schedule[hour]?.set(station, assigned)
                    worked[assigned]?.add(hour)
                    totalWorked[assigned.name] = totalWorked.getOrDefault(assigned.name, 0) + 1
                }
            }
        }
    }
}
