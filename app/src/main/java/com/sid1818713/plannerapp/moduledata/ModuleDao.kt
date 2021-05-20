package com.sid1818713.plannerapp.moduledata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sid1818713.plannerapp.moduledata.model.Module

@Dao
interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addModule(module: Module)

    @Query("SELECT * FROM module_table ORDER BY module ASC")
    fun readAllData(): LiveData<List<Module>>
}