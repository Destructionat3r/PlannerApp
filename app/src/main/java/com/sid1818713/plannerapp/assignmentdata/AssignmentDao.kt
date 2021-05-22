package com.sid1818713.plannerapp.assignmentdata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.moduledata.model.Module

@Dao
interface AssignmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAssignment(assignment: Assignment)

    @Delete
    suspend fun deleteAssignment(assignment: Assignment)

    @Query("DELETE FROM assignment_database WHERE moduleNumber = :moduleNumber")
    fun deleteModuleAssignments(moduleNumber: String)

    @Query("SELECT * FROM assignment_database ORDER BY assignmentDue ASC")
    fun readAllData(): LiveData<List<Assignment>>

    @Query("SELECT * FROM assignment_database WHERE moduleNumber = :moduleNumber ORDER BY assignmentNumber ASC")
    fun readAssignmentData(moduleNumber: String): LiveData<List<Assignment>>
}