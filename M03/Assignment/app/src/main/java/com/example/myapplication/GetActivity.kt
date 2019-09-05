package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.apapters.EverythingAdapter
import com.example.myapplication.model.Employee
import com.example.myapplication.retrofit.ApiInterface
import com.example.myapplication.retrofit.Factory.Companion.create
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {
    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this, "Failed to get", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()
        var employees = response.body()
        recycler.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = EverythingAdapter(employees)
        recycler.layoutManager = manager
        recycler.adapter = adapter
    }

    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)


        apiInterface = create()
        when (intent.getStringExtra("key")) {
            "simple" -> {
                apiInterface.getEmployees().enqueue(this)
            }
            "path" -> {
                apiInterface.getEmployeeById("2").enqueue(this)
            }
            "query" -> {
                apiInterface.getEmployeeByAge("45").enqueue(this)
            }
        }
    }
}
