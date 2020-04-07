package com.example.maket.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maket.R
import com.example.maket.Shift
import com.example.maket.ShiftAdapter
import com.example.maket.shiftApiInterface
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaceholderFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): PlaceholderFragment = PlaceholderFragment()
    }

    private val list: MutableList<Shift> = mutableListOf(
        Shift(date = 1561961173, status = "Не назначен", id = 1, workerID = 1, category = "Повар"),
        Shift(date = 1561961173, status = "Назначен", id = 2, workerID = 2, category = "официант"),
        Shift(date = 1562047573, status = "Не назначен", id = 3, workerID = 1, category = "Повар"),
        Shift(date = 1562047573, status = "Не назначен", id = 4, workerID = 2, category = "официант"),
        Shift(date = 1562123973, status = "Назначен", id = 5, workerID = 1, category = "Повар"),
        Shift(date = 1562123973, status = "Назначен", id = 6, workerID = 2, category = "официант"),
        Shift(date = 1562210373, status = "Назначен", id = 7, workerID = 1, category = "Повар"),
        Shift(date = 1562210373, status = "Назначен", id = 8, workerID = 2, category = "официант"),
        Shift(date = 1562296773, status = "Не назначен", id = 9, workerID = 1, category = "Повар"),
        Shift(date = 1562296773, status = "Не назначен", id = 10, workerID = 2, category = "официант"),
        Shift(date = 1562383173, status = "Назначен", id = 11, workerID = 1, category = "Повар"),
        Shift(date = 1562383173, status = "Не назначен", id = 12, workerID = 2, category = "официант"),
        Shift(date = 1562470173, status = "Назначен принудительно", id = 13, workerID = 1, category = "Повар"),
        Shift(date = 1562470173, status = "Назначен принудительно", id = 14, workerID = 2, category = "официант")
    )
    var myShifts = mutableListOf<Shift>()

    private val adapter = ShiftAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_first.adapter = adapter
        rv_first.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        getShiftslist()

        val id = arguments?.getInt("workerId")?: 1
        var Shift_on_week: List<Shift> = filterShifts(id, list) ?: filterShifts(1, list)
        adapter.showShiftList(Shift_on_week)
        adapter.notifyDataSetChanged()
    }

    fun filterShifts(workerID: Int, myShifts: MutableList<Shift>): List<Shift> {
        var newlist: List<Shift> = myShifts.filter { it.workerID == workerID }
        return newlist
    }


    fun getShiftslist() {
        val apiService = shiftApiInterface.create()
        val call = apiService.getShiftsDetails()
        call.enqueue(object : Callback<List<Shift>> {
            override fun onFailure(call: Call<List<Shift>>, t: Throwable) {
                //Toast.makeText(this@PlaceholderFragment, "FailCallback", Toast.LENGTH_LONG).show()
                Log.d("Test", t.localizedMessage)

            }

            override fun onResponse(call: Call<List<Shift>>, response: Response<List<Shift>>?) {
                myShifts = response?.body()!!.toMutableList() ?: mutableListOf<Shift>(Shift(0, 0, 0, "0"))
                //Log.d("Test", myShifts[0].toString())
                //Toast.makeText(this@MainActivity, "Connected", Toast.LENGTH_LONG).show()
            }
        })
        // Log.d("Test", adapter.toString())
    }
}