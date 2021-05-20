package com.sid1818713.plannerapp.fragments.modules.moduleinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.sid1818713.plannerapp.R
import kotlinx.android.synthetic.main.fragment_module_info.view.*

class ModuleInfoFragment : Fragment() {
    private val args by navArgs<ModuleInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_module_info, container, false)

        view.moduleTitle_txt.text = args.currentModule.module

        return view
    }
}