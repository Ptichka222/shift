package com.example.shiftscedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Member(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val access1: Boolean,
    val access2: Boolean,
    val access3: Boolean,
    val access4: Boolean
)
