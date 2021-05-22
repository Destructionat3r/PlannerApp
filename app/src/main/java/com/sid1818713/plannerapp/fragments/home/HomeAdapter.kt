package com.sid1818713.plannerapp.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.assignmentdata.model.Assignment
import kotlinx.android.synthetic.main.custom_home_row.view.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var assignmentList = emptyList<Assignment>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_home_row, parent, false))
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = assignmentList[position]
        holder.itemView.homeAssignmentDetails.text = "${currentItem.moduleNumber}\nElement ${currentItem.assignmentNumber} - ${currentItem.assignmentDetails}"
        holder.itemView.homeAssignmentDue.text = currentItem.assignmentDue
    }

    fun setData(assignment: List<Assignment>) {
        this.assignmentList = assignment
        notifyDataSetChanged()
    }
}