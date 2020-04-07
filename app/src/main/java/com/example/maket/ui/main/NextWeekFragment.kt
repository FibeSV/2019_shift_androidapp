package com.example.maket.ui.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maket.NextWeekAdapter

import com.example.maket.R
import com.example.maket.Shift
import kotlinx.android.synthetic.main.fragment_next_week.*
import java.util.*

class NextWeekFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = NextWeekFragment()
    }

    private var list: MutableList<Shift> = mutableListOf(
        Shift(date = 1562555973, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562642373, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562728773, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562815173, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562901573, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562987973, status = "Не назначен", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1563074373, status = "Не назначен", id = 13, workerID = 1, category = "Повар")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = NextWeekAdapter(context!!)

        var args = arguments
        Log.d("Test", args.toString())//!!.getString("index", "default"))


        rv_next_week.adapter = adapter
        rv_next_week.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter.showShiftList(list)

        next_week_button.setOnClickListener { send_button(adapter) }
    }

    private fun send_button(adapter: NextWeekAdapter) {
        val myToast = Toast.makeText(requireContext(), "Рекомендация отправлена!", Toast.LENGTH_SHORT)
        myToast.show()
        adapter.clear()
    }


}
