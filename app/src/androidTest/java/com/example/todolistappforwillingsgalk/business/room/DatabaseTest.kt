package com.example.todolistappforwillingsgalk.business.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TaskDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup(){
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.taskDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addTaskInDatabase()= runBlocking{
        val task = CacheTask(
            isCompleted = true,
            isDone = true,
            task = "Lorem ipsum bla bla bla",
            serialNo = 1
        )
        dao.InsertTask(task = task)
        val allStocks = dao.GetAllTasks()
        assertThat(allStocks).contains(task)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkAutoGenerate()= runBlocking{
        val task = CacheTask(
            isCompleted = true,
            isDone = true,
            task = "Lorem ipsum bla bla bla",
            serialNo = null
        )
        dao.InsertTask(task = task)
        val allStocks = dao.GetAllTasks()
        assertThat(allStocks).isNotEmpty()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkDoneTask()= runBlocking{
        val task = CacheTask(
            isCompleted = true,
            isDone = false,
            task = "Lorem ipsum bla bla bla",
            serialNo = 1
        )
        dao.InsertTask(task = task)
        dao.TaskDone(1)
        val doneTasks = dao.GetRemainingTasks()
        assertThat(doneTasks.get(0).isDone).isTrue()
    }



}