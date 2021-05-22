package com.sid1818713.plannerapp.fragments.modules.listmodules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.moduledata.viewmodel.ModuleViewModel
import kotlinx.android.synthetic.main.fragment_list_modules.view.*

class ListModulesFragment : Fragment() {
    private lateinit var mModuleViewModel: ModuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_modules, container, false)

        // Recyclerview
        val adapter = ListModulesAdapter()
        val recyclerView = view.moduleRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ModuleViewModel
        mModuleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)
        mModuleViewModel.readAllData.observe(viewLifecycleOwner, Observer { module ->
            adapter.setData(module)
        })

        view.moduleFab.setOnClickListener {
            findNavController().navigate(R.id.action_listModulesFragment_to_addModulesFragment)
        }

        return view
    }
}