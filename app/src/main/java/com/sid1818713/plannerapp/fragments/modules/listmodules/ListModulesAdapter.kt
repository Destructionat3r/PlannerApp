package com.sid1818713.plannerapp.fragments.modules.listmodules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sid1818713.plannerapp.R
import com.sid1818713.plannerapp.moduledata.model.Module
import kotlinx.android.synthetic.main.custom_module_row.view.*

class ListModulesAdapter: RecyclerView.Adapter<ListModulesAdapter.MyViewHolder>() {
    private var moduleList = emptyList<Module>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_module_row, parent, false))
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = moduleList[position]
        holder.itemView.module_btn.text = currentItem.module

        holder.itemView.module_btn.setOnClickListener {
            val action = ListModulesFragmentDirections.actionListModulesFragmentToModuleInfoFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(module: List<Module>) {
        this.moduleList = module
        notifyDataSetChanged()
    }
}