package com.sid1818713.plannerapp.fragments.modules.moduleinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import kotlinx.android.synthetic.main.custom_assignment_row.view.*

class ModuleInfoAdapter: RecyclerView.Adapter<ModuleInfoAdapter.MyViewHolder>() {
    private var assignmentList = emptyList<Assignment>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_assignment_row, parent, false))
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = assignmentList[position]
        holder.itemView.assignmentNumber_txt.text = currentItem.assignmentNumber
        holder.itemView.assignmentDetails_txt.text = currentItem.assignmentDetails
        holder.itemView.assignmentDueDate_txt.text = currentItem.assignmentDue

        holder.itemView.assignmentRowLayout.setOnClickListener {
            val action = ModuleInfoFragmentDirections.actionModuleInfoFragmentToUpdateAssignmentFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(assignment: List<Assignment>) {
        this.assignmentList = assignment
        notifyDataSetChanged()
    }
}