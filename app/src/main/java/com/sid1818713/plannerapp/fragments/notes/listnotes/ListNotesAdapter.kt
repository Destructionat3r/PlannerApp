package com.sid1818713.plannerapp.fragments.notes.listnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.notedata.model.Note
import kotlinx.android.synthetic.main.custom_note_row.view.*

class ListNotesAdapter: RecyclerView.Adapter<ListNotesAdapter.MyViewHolder>() {
    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_note_row, parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.note_btn.text = "${currentItem.noteTitle}\n\n${currentItem.noteContents}"

        holder.itemView.note_btn.setOnClickListener {
            val action = ListNotesFragmentDirections.actionListNotesFragmentToUpdateNotesFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}