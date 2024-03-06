package com.devtests.pruebatecnica

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val list:ArrayList<DataModelItem>, val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var onItemClickListener:((DataModelItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (DataModelItem) -> Unit) {
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val idTxt:TextView = itemView.findViewById(R.id.id_txt)
        val nameTxt:TextView = itemView.findViewById(R.id.name_txt)
        val typeTxt:TextView = itemView.findViewById(R.id.type_txt)
        val ppuTxt:TextView = itemView.findViewById(R.id.ppu_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.apply {
            idTxt.text = currentItem.id.toString()
            nameTxt.text = currentItem.name
            typeTxt.text = currentItem.type
            ppuTxt.text = currentItem.ppu.toString()
        }
        holder.itemView.setOnClickListener {
           onItemClickListener?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}