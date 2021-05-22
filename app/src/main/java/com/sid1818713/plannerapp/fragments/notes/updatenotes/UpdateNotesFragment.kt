package com.sid1818713.plannerapp.fragments.notes.updatenotes

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.notedata.model.Note
import com.sid1818713.plannerapp.notedata.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update_notes.*
import kotlinx.android.synthetic.main.fragment_update_notes.view.*

class UpdateNotesFragment : Fragment() {
    private val args by navArgs<UpdateNotesFragmentArgs>()

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_notes, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.updateNoteTitle_et.setText(args.currentNote.noteTitle)
        view.updateNoteContents_et.setText(args.currentNote.noteContents)

        view.updateNote_btn.setOnClickListener {
            updateNote()
        }

        view.deleteNote_btn.setOnClickListener {
            deleteNote()
        }

        return view
    }

    private fun updateNote() {
        val noteTitle = updateNoteTitle_et.text.toString()
        val noteContents = updateNoteContents_et.text.toString()

        if(inputCheck(noteTitle, noteContents)) {
            // Create Note Object
            val updatedNote = Note(args.currentNote.id, noteTitle, noteContents)

            //Update Current Note
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_SHORT).show()

            //Navigate Back
            findNavController().navigate(R.id.action_updateNotesFragment_to_listNotesFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(noteTitle: String, noteContents: String): Boolean {
        return !(TextUtils.isEmpty(noteTitle) || TextUtils.isEmpty(noteContents))
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), "${args.currentNote.noteTitle} deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNotesFragment_to_listNotesFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.noteTitle}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.noteTitle}")
        builder.create().show()
    }
}