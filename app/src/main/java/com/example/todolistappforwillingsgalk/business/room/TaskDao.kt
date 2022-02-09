package com.example.todolistappforwillingsgalk.business.room

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTask(task: CacheTask): Long

    @Query("SELECT * FROM Tasks")
    suspend fun GetAllTasks(): List<CacheTask>

    @Query("SELECT * FROM Tasks WHERE isDone IS 1")
    suspend fun GetDoneTasks(): List<CacheTask>

    @Query("SELECT * FROM Tasks WHERE isCompleted IS 1")
    suspend fun GetCompletedTasks(): List<CacheTask>

    @Query("UPDATE Tasks SET isDone = 1 WHERE SerialNo = :id")
    suspend fun TaskDone(id: Int)

    @Query("UPDATE Tasks SET isDone = 0 WHERE SerialNo = :id")
    suspend fun TaskUndone(id: Int)

    @Query("UPDATE Tasks SET isCompleted = 1 WHERE SerialNo = :id")
    suspend fun TaskCompleted(id: Int)

    @Query("DELETE FROM Tasks WHERE SerialNo = :id")
    suspend fun DeleteTask(id: Int)

}