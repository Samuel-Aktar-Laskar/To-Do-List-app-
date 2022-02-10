package com.example.todolistappforwillingsgalk.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.business.repository.MainRepository
import com.example.todolistappforwillingsgalk.business.room.CacheTask
import com.example.todolistappforwillingsgalk.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "MainViewModelTag"
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _doneTasks: MutableLiveData<DataState<List<Task>>> = MutableLiveData()
    val doneTasks: LiveData<DataState<List<Task>>>
        get() = _doneTasks

    private val _completedTasks: MutableLiveData<DataState<List<Task>>> = MutableLiveData()
    val completedTasks: LiveData<DataState<List<Task>>>
        get() = _completedTasks

    private val _undoneTasks: MutableLiveData<DataState<List<Task>>> = MutableLiveData()
    val undoneTasks: LiveData<DataState<List<Task>>>
        get() = _undoneTasks

    fun refreshUndoneTasks(){
        viewModelScope.launch {
            mainRepository.GetUndoneTasks().onEach {
                Log.d(TAG, "undontasks: Inside getStocks it :$it")
                _undoneTasks.value = it
            }.launchIn(viewModelScope)
        }

    }

    fun refreshDoneTasks(){
        viewModelScope.launch {
            mainRepository.GetDoneTasks().onEach {
                Log.d(TAG, "refreshDoneTasks: $it")
                _doneTasks.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun refreshCompletedTasks(){
        viewModelScope.launch {
            mainRepository.GetCompletedTasks().onEach {
                Log.d(TAG, "refreshCompletedTasks: $it")
                _completedTasks.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun addTask(task: String){
        viewModelScope.launch {
            mainRepository.InsertTask(
                CacheTask(
                    serialNo = null,
                    task = task,
                    isDone = false,
                    isCompleted = false
                )
            )
        }
    }

    fun TaskDone(id: Int){
        viewModelScope.launch {
            mainRepository.TaskDone(id)
        }
    }

    fun TaskUndone(id: Int){
        viewModelScope.launch {
            mainRepository.TaskUndone(id)
        }
    }

    fun TaskCompleted(id: Int){
        viewModelScope.launch {
            mainRepository.TaskCompleted(id)
        }
    }

    fun delete(id: Int){
        viewModelScope.launch {
            mainRepository.DeleteTask(id)
        }
    }

}