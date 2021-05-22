package com.sid1818713.plannerapp.moduledata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sid1818713.plannerapp.moduledata.ModuleDatabase
import com.sid1818713.plannerapp.moduledata.model.Module
import com.sid1818713.plannerapp.moduledata.repository.ModuleRepository
import com.sid1818713.plannerapp.notedata.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModuleViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ModuleRepository
    private val _moduleNumber = MutableLiveData("Num")
    private val _moduleName = MutableLiveData("Name")
    val moduleNumber: LiveData<String> = _moduleNumber
    val moduleName: LiveData<String> = _moduleName
    val readAllData: LiveData<List<Module>>

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

    fun deleteModule(module: Module) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteModule(module)
        }
    }

    fun saveModule(moduleNumber: String, moduleName: String) {
        _moduleNumber.value = moduleNumber
        _moduleName.value = moduleName
    }
}