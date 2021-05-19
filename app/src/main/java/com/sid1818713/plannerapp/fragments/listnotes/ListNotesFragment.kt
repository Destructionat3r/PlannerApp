package com.sid1818713.plannerapp.fragments.listnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sid1818713.plannerapp.R
import kotlinx.android.synthetic.main.fragment_list_notes.view.*

class ListNotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_notes, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_addNotesFragment)
        }

        return view
    }
}