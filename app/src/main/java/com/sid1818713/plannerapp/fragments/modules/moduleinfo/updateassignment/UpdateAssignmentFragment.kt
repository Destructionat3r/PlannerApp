package com.sid1818713.plannerapp.fragments.modules.moduleinfo.updateassignment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import com.sid1818713.plannerapp.databinding.FragmentUpdateAssignmentBinding
import com.sid1818713.plannerapp.fragments.modules.moduleinfo.ModuleInfoFragmentArgs
import com.sid1818713.plannerapp.moduledata.viewmodel.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_update_assignment.*
import java.util.*

class UpdateAssignmentFragment : Fragment() {
    private lateinit var mAssignmentViewModel: AssignmentViewModel
    private val mModuleViewModel: ModuleViewModel by activityViewModels()
    private val assignmentArgs by navArgs<UpdateAssignmentFragmentArgs>()
    private val binding get() = _binding!!
    private var _binding: FragmentUpdateAssignmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateAssignmentBinding.inflate(inflater, container, false)

        mModuleViewModel.moduleNumber.observe(viewLifecycleOwner) { module ->
            binding.uModuleNumberTxt.setText(module).toString()
        }

        mModuleViewModel.moduleName.observe(viewLifecycleOwner) { module ->
            binding.uModuleNameTxt.setText(module).toString()
        }

        val date = assignmentArgs.currentAssignment.assignmentDue
        val delim = "/"
        val dateSplits = date.split(delim)

        binding.updateAssignmentNumberEt.setText(assignmentArgs.currentAssignment.assignmentNumber)
        binding.updateAssignmentDetailsEt.setText(assignmentArgs.currentAssignment.assignmentDetails)
        binding.updateDueYearEt.setText(dateSplits[0])
        binding.updateDueMonthEt.setText(dateSplits[1])
        binding.updateDueDayEt.setText(dateSplits[2])

        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)

        binding.updateAssignmentBtn.setOnClickListener {
            updateAssignment()
        }

        binding.deleteAssignmentBtn.setOnClickListener {
            deleteAssignment()
        }

        return binding.root
    }

    private fun updateAssignment() {
        val moduleNumber = uModuleNumber_txt.text.toString()
        val assignmentNumber = updateAssignmentNumber_et.text.toString()
        val assignmentDetails = updateAssignmentDetails_et.text.toString()
        val dueYear = updateDueYear_et.text
        val dueMonth = updateDueMonth_et.text
        val dueDay = updateDueDay_et.text
        val dueDate = "$dueYear/$dueMonth/$dueDay"

        if(inputCheck(assignmentNumber, assignmentDetails, dueYear, dueMonth, dueDay)) {
            // Create Assignment Object
            val updatedAssignment = Assignment(assignmentArgs.currentAssignment.id, moduleNumber, assignmentNumber, assignmentDetails, dueDate)

            // Update Database
            mAssignmentViewModel.updateAssignment(updatedAssignment)
            Toast.makeText(requireContext(), "Assignment updated", Toast.LENGTH_SHORT).show()

            //  Add To Calendar
            addCalendar(moduleNumber, assignmentNumber, assignmentDetails, Integer.parseInt(dueYear.toString()), Integer.parseInt(dueMonth.toString()), Integer.parseInt(dueDay.toString()))

            //Navigate Back
            findNavController().popBackStack()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(assignmentNumber: String, assignmentDetails: String, dueYear: Editable, dueMonth: Editable, dueDay: Editable): Boolean {
        return !(TextUtils.isEmpty(assignmentNumber) || TextUtils.isEmpty(assignmentDetails) || TextUtils.isEmpty(dueYear) || TextUtils.isEmpty(dueMonth) || TextUtils.isEmpty(dueDay))
    }

    private fun addCalendar(moduleNumber: String, assignmentNumber: String, assignmentDetails: String, assignmentYear: Int, assignmentMonth: Int, assignmentDay: Int) {
        val calDate = GregorianCalendar(assignmentYear, assignmentMonth, assignmentDay)

        val intent = Intent(Intent.ACTION_INSERT)

        intent.data = CalendarContract.Events.CONTENT_URI
        intent.putExtra(CalendarContract.Events.TITLE, "$moduleNumber - Assignment $assignmentNumber")
        intent.putExtra(CalendarContract.Events.DESCRIPTION, assignmentDetails)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis())
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis())

        startActivity(intent)
    }

    private fun deleteAssignment() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mAssignmentViewModel.deleteAssignment(assignmentArgs.currentAssignment)
            Toast.makeText(requireContext(), "Assignment ${assignmentArgs.currentAssignment.assignmentNumber} deleted", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Assignment ${assignmentArgs.currentAssignment.assignmentNumber}?")
        builder.setMessage("Are you sure you want to delete assignment ${assignmentArgs.currentAssignment.assignmentNumber}")
        builder.create().show()
    }
}