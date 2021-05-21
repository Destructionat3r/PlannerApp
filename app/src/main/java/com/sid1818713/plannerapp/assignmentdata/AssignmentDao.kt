package com.sid1818713.plannerapp.assignmentdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sid1818713.plannerapp.assignmentdata.model.Assignment

@Dao
interface AssignmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAssignment(assignment: Assignment)

    @Query("SELECT * FROM assignment_database ORDER BY moduleNumber ASC")
    fun readAllData(): LiveData<List<Assignment>>

    @Query("SELECT * FROM assignment_database WHERE moduleNumber = :moduleNumber ORDER BY assignmentNumber ASC")
    fun readAssignmentData(moduleNumber: String): LiveData<List<Assignment>>
}