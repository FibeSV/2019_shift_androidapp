package com.example.maket

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class NextWeekAdapter constructor(val context: Context): RecyclerView.Adapter<NextWeekHolder>() {
    val week: MutableList<Shift> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextWeekHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_shift, parent, false)
            .let { NextWeekHolder(it) }

    override fun getItemCount() = week.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NextWeekHolder, position: Int) {
        holder.bind(week[position])
        selectView(holder.itemView, week[position])
        clickView(holder.itemView, week[position])
    }
    fun clickView(view: View, item: Shift) {
        view.setOnClickListener(View.OnClickListener {
            if (item.status=="Отказ")
                item.status="Не назначено"
            else
                item.status="Отказ"
            selectView(it, item)
        })
    }

    fun clear() {
        for (shift: Shift in week) {
            shift.status = "Не назначено"
            notifyDataSetChanged()
        }
    }

    //fun getSelectedList():List<Shift> =
    //    week.filter { it.selected }

    private fun selectView(view: View, item: Shift) {
        if (item.status=="Отказ" ) {
            view.setBackgroundColor(context.resources.getColor(R.color.selected))

        } else {
            view.setBackgroundColor(context.resources.getColor(R.color.standart))
        }
    }

    fun showShiftList(list: List<Shift>) {
        val size = week.size
        week.clear()
        if (size != 0) notifyItemRangeRemoved(0, size)
        week.addAll(list)
        notifyItemRangeInserted(0, week.size)
    }


}

