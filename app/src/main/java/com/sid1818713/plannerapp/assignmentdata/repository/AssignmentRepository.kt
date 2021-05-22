package com.sid1818713.plannerapp.assignmentdata.repository

import androidx.lifecycle.LiveData
import com.sid1818713.plannerapp.assignmentdata.AssignmentDao
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.moduledata.model.Module

class AssignmentRepository(private val assignmentDao: AssignmentDao) {
    val readAllData: LiveData<List<Assignment>> = assignmentDao.readAllData()

    suspend fun addAssignment(assignment: Assignment) {
        assignmentDao.addAssignment(assignment)
    }

    suspend fun deleteAssignment(assignment: Assignment) {
        assignmentDao.deleteAssignment(assignment)
    }

    suspend fun deleteModuleAssignments(moduleNumber: String) {
        assignmentDao.deleteModuleAssignments(moduleNumber)
    }

    fun readAssignmentData(moduleNumber: String): LiveData<List<Assignment>> {
        return assignmentDao.readAssignmentData(moduleNumber)
    }
}