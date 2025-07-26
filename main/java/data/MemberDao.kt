package com.example.shiftscedule.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Update
    suspend fun update(member: Member)

    @Query("SELECT * FROM member")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Member>>

    @Insert
    suspend fun insert(member: Member)

    @Delete
    suspend fun delete(member: Member)
}

