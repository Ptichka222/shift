package com.example.shiftscedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shiftscedule.data.AppDatabase
import com.example.shiftscedule.data.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemberViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).memberDao()

    // We use StateFlow instead of LiveData for Compose
    private val _members = MutableStateFlow<List<Member>>(emptyList())
    val members: StateFlow<List<Member>> = _members

    init {
        viewModelScope.launch {
            dao.getAll().collect { memberList ->
                _members.value = memberList
            }
        }
    }

    fun addMember(member: Member) = viewModelScope.launch {
        dao.insert(member)
    }

    fun updateMember(member: Member) = viewModelScope.launch {
        dao.update(member)
    }

    fun deleteMember(member: Member) = viewModelScope.launch {
        dao.delete(member)
    }
}
