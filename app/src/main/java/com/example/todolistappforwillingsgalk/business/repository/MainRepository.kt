package com.example.todolistappforwillingsgalk.business.repository

import com.example.todolistappforwillingsgalk.business.room.CacheMapper
import com.example.todolistappforwillingsgalk.business.room.TaskDao

class MainRepository
constructor(
    val cacheMapper: CacheMapper,
    val taskDao: TaskDao
){

}