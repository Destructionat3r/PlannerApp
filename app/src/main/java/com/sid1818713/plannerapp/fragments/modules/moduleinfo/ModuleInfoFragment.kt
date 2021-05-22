package com.sid1818713.plannerapp.fragments.modules.moduleinfo

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import com.sid1818713.plannerapp.databinding.FragmentModuleInfoBinding
import com.sid1818713.plannerapp.moduledata.viewmodel.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_module_info.view.*

class ModuleInfoFragment : Fragment() {
    private val args by navArgs<ModuleInfoFragmentArgs>()
    private lateinit var mAssignmentViewModel: AssignmentViewModel
    private val mModuleViewModel: ModuleViewModel by activityViewModels()

    private var _binding: FragmentModuleInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModuleInfoBinding.inflate(inflater, container, false)

        binding.moduleTitleTxt.setText(args.currentModule.module)
        mModuleViewModel.saveModule(args.currentModule.moduleNumber, args.currentModule.module)

        // RecyclerView
        val adapter = ModuleInfoAdapter()
        val recyclerview = binding.assignmentRecyclerView
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // AssignmentViewModel
        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        mAssignmentViewModel.readAssignmentData(args.currentModule.moduleNumber).observe(viewLifecycleOwner, Observer { assignment ->
            adapter.setData(assignment)
        })

        binding.assignmentFab.setOnClickListener {
            findNavController().navigate(R.id.action_moduleInfoFragment_to_addAssignmentFragment)
        }

        binding.deleteModuleBtn.setOnClickListener {
            deleteModule()
        }

        return binding.root
    }

    private fun deleteModule() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mModuleViewModel.deleteModule(args.currentModule)
            mAssignmentViewModel.deleteModuleAssignments(args.currentModule.moduleNumber)
            Toast.makeText(requireContext(), "${args.currentModule.module} deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_moduleInfoFragment_to_listModulesFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentModule.module}?")
        builder.setMessage("Are you sure you want to delete ${args.currentModule.module}")
        builder.create().show()
    }
}