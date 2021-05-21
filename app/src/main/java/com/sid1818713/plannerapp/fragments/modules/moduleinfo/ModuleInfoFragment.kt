package com.sid1818713.plannerapp.fragments.modules.moduleinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
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
            mModuleViewModel.saveModule(args.currentModule.moduleNumber)
            findNavController().navigate(R.id.action_moduleInfoFragment_to_addAssignmentFragment)
        }

        return binding.root
    }
}