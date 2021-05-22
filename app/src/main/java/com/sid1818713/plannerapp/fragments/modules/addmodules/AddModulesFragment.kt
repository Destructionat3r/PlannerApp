package com.sid1818713.plannerapp.fragments.modules.addmodules

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.moduledata.model.Module
import com.sid1818713.plannerapp.moduledata.viewmodel.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_add_modules.*
import kotlinx.android.synthetic.main.fragment_add_modules.view.*

class AddModulesFragment : Fragment() {
    private lateinit var mModuleViewModel: ModuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_modules, container, false)
        mModuleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)

        view.addModule_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val moduleNumber = addModuleNumber_et.text.toString()
        val moduleName = addModuleName_et.text.toString()

        if (inputCheck(moduleName)) {
            // Create Module Object
            val module = Module(0, moduleNumber, moduleName)

            // Add Data to Database
            mModuleViewModel.addModule(module)
            Toast.makeText(requireContext(), "Module added", Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_addModulesFragment_to_listModulesFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(moduleName: String): Boolean {
        return !(TextUtils.isEmpty(moduleName))
    }
}