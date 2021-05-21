package com.sid1818713.plannerapp.fragments.modules.moduleinfo.addassignment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import com.sid1818713.plannerapp.fragments.modules.moduleinfo.ModuleInfoFragmentArgs
import kotlinx.android.synthetic.main.fragment_add_assignment.*
import kotlinx.android.synthetic.main.fragment_add_assignment.view.*

class AddAssignmentFragment : Fragment() {
    private val args by navArgs<ModuleInfoFragmentArgs>()
    private lateinit var mAssignmentViewModel: AssignmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_assignment, container, false)

        //view.dueDate_txt.text = args.currentModule.moduleNumber

        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)

        view.addAssignment_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val assignmentNumber = addAssignmentNumber_et.text.toString()
        val assignmentDetails = addAssignmentDetails_et.text.toString()
        val dueYear = addDueYear_et.text
        val dueMonth = addDueMonth_et.text
        val dueDay = addDueDay_et.text
        val dueDate = "$dueYear/$dueMonth/$dueDay"

        if(inputCheck(assignmentNumber, assignmentDetails, dueYear, dueMonth, dueDay)) {
            // Create Assignment Object
            val assignment = Assignment(0,"M907", assignmentNumber, assignmentDetails, dueDate)

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