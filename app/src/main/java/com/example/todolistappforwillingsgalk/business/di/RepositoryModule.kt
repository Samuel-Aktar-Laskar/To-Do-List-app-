package com.example.todolistappforwillingsgalk.business.di

import com.example.todolistappforwillingsgalk.business.repository.MainRepository
import com.example.todolistappforwillingsgalk.business.room.CacheMapper
import com.example.todolistappforwillingsgalk.business.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        cacheMapper: CacheMapper,
        taskDao: TaskDao
    ) : MainRepository {
        return MainRepository(
            cacheMapper = cacheMapper,
            taskDao = taskDao
        )
    }
}