package com.example.todolistappforwillingsgalk.business.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class CacheTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SerialNo")
    val serialNo: Int?,

    @ColumnInfo(name = "Task")
    val task: String,

    @ColumnInfo(name = "isDone") //Done means it is struck out in RemainingTask tab
    val isDone: Boolean,

    @ColumnInfo(name = "isCompleted") //Completed means it is in CompletedTask tab
    val isCompleted : Boolean
)
