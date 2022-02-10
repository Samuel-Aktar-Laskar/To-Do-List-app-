package com.example.todolistappforwillingsgalk.business.repository

import android.util.Log
import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.business.room.CacheMapper
import com.example.todolistappforwillingsgalk.business.room.CacheTask
import com.example.todolistappforwillingsgalk.business.room.TaskDao
import com.example.todolistappforwillingsgalk.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


private const val TAG = "MainRepositoryLog"

class MainRepository
constructor(
    val cacheMapper: CacheMapper,
    val taskDao: TaskDao
) {
    suspend fun GetUndoneTasks(): Flow<DataState<List<Task>>> = flow {
        emit(DataState.Loading)
        try {
            val _tasks = taskDao.GetUnDoneTasks()
            val tasks = cacheMapper.mapFromEntityList(_tasks)
            emit(DataState.Success(tasks))
        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.d(TAG, "GetUndoneTasks: Error: ${e.localizedMessage}")
        }
    }

    suspend fun GetDoneTasks(): Flow<DataState<List<Task>>> = flow {
        emit(DataState.Loading)
        try {
            val _tasks = taskDao.GetDoneTasks()
            val tasks = cacheMapper.mapFromEntityList(_tasks)
            emit(DataState.Success(tasks))
        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.d(TAG, "GetUndoneTasks: Error: ${e.localizedMessage}")
        }
    }

    suspend fun GetCompletedTasks(): Flow<DataState<List<Task>>> = flow {
        emit(DataState.Loading)
        try {
            val _tasks = taskDao.GetCompletedTasks()
            val tasks = cacheMapper.mapFromEntityList(_tasks)
            emit(DataState.Success(tasks))
        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.d(TAG, "GetUndoneTasks: Error: ${e.localizedMessage}")
        }
    }


    suspend fun InsertTask(cacheTask: CacheTask){
        try {
            taskDao.InsertTask(cacheTask)
        } catch (e: Exception){
            Log.d(TAG, "InsertTask: Error :${e.localizedMessage}")
        }
    }

    suspend fun TaskDone(id: Int){
        try {
            taskDao.TaskDone(id)
        } catch (e: Exception){
            Log.d(TAG, "TaskDone: Error :${e.localizedMessage}")
        }
    }

    suspend fun TaskUndone(id: Int){
        try {
            taskDao.TaskUndone(id)
        }catch (e: Exception){
            Log.d(TAG, "TaskUndone: Error :${e.localizedMessage}")
        }
    }

    suspend fun TaskCompleted(id: Int){
        try {
            taskDao.TaskCompleted(id)
        } catch (e: Exception){
            Log.d(TAG, "TaskCompleted: Error :${e.localizedMessage}")
        }
    }

    suspend fun DeleteTask(id: Int){
        try {
            taskDao.DeleteTask(id)
        } catch (e: Exception){
            Log.d(TAG, "DeleteTask: Error :${e.localizedMessage}")
        }
    }


}