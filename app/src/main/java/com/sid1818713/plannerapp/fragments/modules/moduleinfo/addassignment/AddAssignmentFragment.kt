package com.sid1818713.plannerapp.fragments.modules.moduleinfo.addassignment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import com.sid1818713.plannerapp.databinding.FragmentAddAssignmentBinding
import com.sid1818713.plannerapp.fragments.modules.moduleinfo.ModuleInfoFragmentArgs
import com.sid1818713.plannerapp.moduledata.viewmodel.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_add_assignment.*
import kotlinx.android.synthetic.main.fragment_add_assignment.view.*

class AddAssignmentFragment : Fragment() {
    private val args by navArgs<ModuleInfoFragmentArgs>()
    private lateinit var mAssignmentViewModel: AssignmentViewModel
    private val mModuleViewModel: ModuleViewModel by activityViewModels()

    private var _binding: FragmentAddAssignmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAssignmentBinding.inflate(inflater, container, false)

        mModuleViewModel.module.observe(viewLifecycleOwner) { module ->
            binding.moduleNumberTxt.setText(module).toString()
        }

        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)

        binding.addAssignmentBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val moduleNumber = moduleNumber_txt.text.toString()
        val assignmentNumber = addAssignmentNumber_et.text.toString()
        val assignmentDetails = addAssignmentDetails_et.text.toString()
        val dueYear = addDueYear_et.text
        val dueMonth = addDueMonth_et.text
        val dueDay = addDueDay_et.text
        val dueDate = "$dueYear/$dueMonth/$dueDay"

        if(inputCheck(assignmentNumber, assignmentDetails, dueYear, dueMonth, dueDay)) {
            // Create Assignment Object
            val assignment = Assignment(0, moduleNumber, assignmentNumber, assignmentDetails, dueDate)

            // Add Data to Database
            mAssignmentViewModel.addAssignment(assignment)
            Toast.makeText(requireContext(), "Assignment added", Toast.LENGTH_SHORT).show()

            //Navigate Back
            findNavController().popBackStack()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(assignmentNumber: String, assignmentDetails: String, dueYear: Editable, dueMonth: Editable, dueDay: Editable): Boolean {
        return !(TextUtils.isEmpty(assignmentNumber) || TextUtils.isEmpty(assignmentDetails) || TextUtils.isEmpty(dueYear) || TextUtils.isEmpty(dueMonth) || TextUtils.isEmpty(dueDay))
    }
}