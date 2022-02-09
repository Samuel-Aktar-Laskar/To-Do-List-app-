package com.example.todolistappforwillingsgalk.business.di

import android.content.Context
import androidx.room.Room
import com.example.todolistappforwillingsgalk.business.room.TaskDao
import com.example.todolistappforwillingsgalk.business.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideTaskDb(@ApplicationContext context: Context): TaskDatabase {
        return Room
            .databaseBuilder(
                context,
                TaskDatabase::class.java,
                TaskDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDAO(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }
}