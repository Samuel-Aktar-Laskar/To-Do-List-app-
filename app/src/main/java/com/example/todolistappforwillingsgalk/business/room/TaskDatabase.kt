package com.example.todolistappforwillingsgalk.business.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database( entities = [CacheTask::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object {
        val DATABASE_NAME: String = "Aktar"
    }
}