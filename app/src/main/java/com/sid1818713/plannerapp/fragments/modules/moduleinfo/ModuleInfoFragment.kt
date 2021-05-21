package com.sid1818713.plannerapp.fragments.modules.moduleinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import kotlinx.android.synthetic.main.fragment_module_info.view.*

class ModuleInfoFragment : Fragment() {
    private val args by navArgs<ModuleInfoFragmentArgs>()
    private lateinit var mAssignmentViewModel: AssignmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_module_info, container, false)

        view.moduleTitle_txt.text = args.currentModule.module

        // RecyclerView
        val adapter = ModuleInfoAdapter()
        val recyclerview = view.assignmentRecyclerView
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // AssignmentViewModel
        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        mAssignmentViewModel.readAssignmentData(args.currentModule.moduleNumber).observe(viewLifecycleOwner, Observer { assignment ->
            adapter.setData(assignment)
        })

        view.assignmentFab.setOnClickListener {
            findNavController().navigate(R.id.action_moduleInfoFragment_to_addAssignmentFragment)
        }

        return view
    }
}