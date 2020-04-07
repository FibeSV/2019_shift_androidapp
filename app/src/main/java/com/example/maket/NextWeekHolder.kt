package com.example.maket

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vh_shift.view.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


class NextWeekHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(item:Shift){
        val dateTime = LocalDateTime.ofEpochSecond(item.date, 0, ZoneOffset.UTC)
        val formatter = DateTimeFormatter.ofPattern("EEEE,MMMM d,yyyy ", Locale.ENGLISH)
        val formattedDate = dateTime.format(formatter)
        itemView.date.text = formattedDate
        itemView.state.text = "Смена 12:00-21:00"
    }

}