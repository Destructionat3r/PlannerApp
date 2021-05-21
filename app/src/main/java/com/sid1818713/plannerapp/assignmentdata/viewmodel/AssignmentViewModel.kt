package com.sid1818713.plannerapp.assignmentdata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sid1818713.plannerapp.assignmentdata.AssignmentDatabase
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.assignmentdata.repository.AssignmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignmentViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Assignment>>
    private val repository: AssignmentRepository

    init {
        val assignmentDao = AssignmentDatabase.getDatabase(application).assignmentDao()
        repository = AssignmentRepository(assignmentDao)
        readAllData = repository.readAllData
    }

    fun addAssignment(assignment: Assignment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAssignment(assignment)
        }
    }

    fun readAssignmentData(moduleNumber: String): LiveData<List<Assignment>> {
        return repository.readAssignmentData(moduleNumber)
    }
}