package com.example.maket

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.maket.ui.main.PlaceholderFragment
import com.example.maket.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        var myWorkers : MutableList<Worker> = mutableListOf(Worker(0, "noname", "null", "0"))
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myWorkers)

        fun newInstance(index: Int): PlaceholderFragment {
            val f = PlaceholderFragment()
            // Supply index input as an argument.
            val args = Bundle()
            args.putInt("index", index)
            f.arguments = args
            return f
        }

        fun getWorkerlist(){
            val apiService = ApiInterface.create()
            val call = apiService.getCategoryDetails()


            call.enqueue(object: Callback<List<Worker>> {
                override fun onFailure(call: Call<List<Worker>>, t: Throwable){
                    Toast.makeText(this@MainActivity, "FailCallback", Toast.LENGTH_LONG).show()
                    Log.d("Test", t.localizedMessage)

                }

                override fun onResponse(call: Call<List<Worker>>, response: Response<List<Worker>>?) {
                    myWorkers = response?.body()!!.toMutableList() ?: mutableListOf<Worker>(Worker(0, "noname", "null", "0"))
                    //Log.d("Test", myWorkers[0].toString())
                    Toast.makeText(this@MainActivity, "Connected", Toast.LENGTH_LONG).show()
                    adapter.remove(Worker(0, "noname", "null", "0"))
                    adapter.addAll(myWorkers)
                   // adapter.notifyDataSetChanged()

                    //Log.d("Test", adapter.isEmpty.toString())
                    spinner.setAdapter(adapter)

                }


            })
           // Log.d("Test", adapter.toString())
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val fragment = PlaceholderFragment()
                fragment.arguments?.putInt("workerId",myWorkers[p2].id)
                Toast.makeText(this@MainActivity, myWorkers[p2].workerName, Toast.LENGTH_LONG).show()
            }
        }



        getWorkerlist()

       // fun onWorkerSelected(workerID: Int ) {
       //    // подключаем FragmentManager
       //     val fragmentManager : FragmentManager  = getSupportFragmentManager()

       //    // Получаем ссылку на второй фрагмент по ID
       //     val fragment2: PlaceholderFragment  = fragmentManager.findFragmentById(R.)

       //    // Выводим нужную информацию
       //    if (fragment2 != null)
       //        fragment2.setDescription(workerID)
       //}

        //Log.d("Test", myWorkers[0].toString())
        //val myWorkers = mutableListOf<Worker>(
        //    Worker(1, "Портос", "официант", 873555048),
        //    Worker(1, "Арамис", "повар", 873555048),
        //    Worker(1, "Д'артаньян", "менеджер", 873555048),
        //    Worker(1, "Петр Сергеевич", "админ", 873555048),
        //    Worker(1, "Валерия Леонидовна", "официант", 873555048)
        //)


    }
}


