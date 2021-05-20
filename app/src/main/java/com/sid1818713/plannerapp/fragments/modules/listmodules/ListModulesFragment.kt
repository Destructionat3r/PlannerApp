package com.sid1818713.plannerapp.fragments.modules.listmodules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sid1818713.plannerapp.R
import kotlinx.android.synthetic.main.fragment_list_modules.view.*

class ListModulesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_modules, container, false)

        view.moduleFab.setOnClickListener {
            findNavController().navigate(R.id.action_listModulesFragment_to_addModulesFragment)
        }

        return view
    }
}