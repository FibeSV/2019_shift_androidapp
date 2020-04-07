package com.example.maket

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class ShiftAdapter  : RecyclerView.Adapter<ShiftViewHolder>() {
    private val week: MutableList<Shift> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_shift, parent, false)
            .let {
                ShiftViewHolder(it)
            }


    override fun getItemCount() = week.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
        holder.bind(week[position])

    }

    fun showShiftList(list: List<Shift>) {
        val size = week.size
        week.clear()
        if (size != 0) notifyItemRangeRemoved(0, size)
        week.addAll(list)
        notifyItemRangeInserted(0, week.size)
    }


}

