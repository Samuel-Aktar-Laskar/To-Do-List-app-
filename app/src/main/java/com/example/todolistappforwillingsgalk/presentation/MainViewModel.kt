package com.example.todolistappforwillingsgalk.presentation

import android.widget.HeaderViewListAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.business.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _doneTasks: MutableLiveData<List<Task>> = MutableLiveData()
    val doneTasks: LiveData<List<Task>>
        get() = _doneTasks

    private val _completedTasks: MutableLiveData<List<Task>> = MutableLiveData()
    val completedTasks: LiveData<List<Task>>
        get() = _completedTasks

    private val _undoneTasks: MutableLiveData<List<Task>> = MutableLiveData()
    val undoneTasks: LiveData<List<Task>>
        get() = _undoneTasks

    fun refreshUndoneTasks(){

    }

    fun refreshDoneTasks(){

    }

    fun refreshCompletedTasks(){

    }

    fun TaskDone(id: Int){

    }

    fun TaskUndone(id: Int){

    }

    fun TaskCompleted(id: Int){

    }

    fun delete(id: Int){

    }

}