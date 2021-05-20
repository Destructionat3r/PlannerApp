package com.sid1818713.plannerapp.moduledata.repository

import androidx.lifecycle.LiveData
import com.sid1818713.plannerapp.moduledata.ModuleDao
import com.sid1818713.plannerapp.moduledata.model.Module

class ModuleRepository(private val moduleDao: ModuleDao) {
    val readAllData: LiveData<List<Module>> = moduleDao.readAllData()

    suspend fun addModule(module: Module) {
        moduleDao.addModule(module)
    }
}