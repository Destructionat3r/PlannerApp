package com.sid1818713.plannerapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.viewmodel.AssignmentViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class Home : Fragment() {
    private lateinit var mAssignmentViewModel: AssignmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // RecyclerView
        val adapter = HomeAdapter()
        val recyclerView = view.homeAssignment_rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // AssignmentViewModel
        mAssignmentViewModel = ViewModelProvider(this).get(AssignmentViewModel::class.java)
        mAssignmentViewModel.readAllData.observe(viewLifecycleOwner, Observer { assignment ->
            adapter.setData(assignment)
        })

        return view
    }
}