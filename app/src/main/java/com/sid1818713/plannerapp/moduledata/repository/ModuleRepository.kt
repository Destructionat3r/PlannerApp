package com.sid1818713.plannerapp.moduledata.repository

import androidx.lifecycle.LiveData
import com.sid1818713.plannerapp.moduledata.ModuleDao
import com.sid1818713.plannerapp.moduledata.model.Module
import com.sid1818713.plannerapp.notedata.model.Note

class ModuleRepository(private val moduleDao: ModuleDao) {
    val readAllData: LiveData<List<Module>> = moduleDao.readAllData()

    suspend fun addModule(module: Module) {
        moduleDao.addModule(module)
    }

    suspend fun deleteModule(module: Module) {
        moduleDao.deleteModule(module)
    }
}