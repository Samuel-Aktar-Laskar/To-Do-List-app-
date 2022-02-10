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

    private val _remainingTasks: MutableLiveData<DataState<List<Task>>> = MutableLiveData()
    val remainingTasks: LiveData<DataState<List<Task>>>
        get() = _remainingTasks

    private val _completedTasks: MutableLiveData<DataState<List<Task>>> = MutableLiveData()
    val completedTasks: LiveData<DataState<List<Task>>>
        get() = _completedTasks


    fun refreshRemainingTasks(){
        viewModelScope.launch {
            mainRepository.GetRemainingTasks().onEach {
                Log.d(TAG, "refreshDoneTasks: $it")
                _remainingTasks.value = it
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
                    task = task,
                    isDone = false,
                    isCompleted = false
                )
            )
            refreshRemainingTasks()
        }
    }

    fun TaskDone(id: Int){
        viewModelScope.launch {
            mainRepository.TaskDone(id)
            refreshRemainingTasks()
        }
    }

    fun TaskUndone(id: Int){
        viewModelScope.launch {
            mainRepository.TaskUndone(id)
            refreshRemainingTasks()
        }
    }

    fun ClearCompletedTasks(){
        viewModelScope.launch {
            mainRepository.ClearCompletedTask()
            refreshRemainingTasks()
            refreshCompletedTasks()
        }
    }

    fun delete(id: Int, isR: Boolean){
        viewModelScope.launch {
            mainRepository.DeleteTask(id)
            if (isR) refreshRemainingTasks() else refreshCompletedTasks()
        }
    }

}