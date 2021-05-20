package com.sid1818713.plannerapp.moduledata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sid1818713.plannerapp.moduledata.ModuleDatabase
import com.sid1818713.plannerapp.moduledata.model.Module
import com.sid1818713.plannerapp.moduledata.repository.ModuleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModuleViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Module>>
    private val repository: ModuleRepository

    init {
        val moduleDao = ModuleDatabase.getDatabase(application).moduleDao()
        repository = ModuleRepository(moduleDao)
        readAllData = repository.readAllData
    }

    fun addModule(module: Module) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addModule(module)
        }
    }
}