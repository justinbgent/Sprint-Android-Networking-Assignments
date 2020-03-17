package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.apapters.EverythingAdapter
import com.example.myapplication.model.Employee
import com.example.myapplication.retrofit.ApiInterface
import com.example.myapplication.retrofit.Factory
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        var item: List<Employee>? = listOf()
        if (response.body() != null){
            var nonNullEmployee: Employee = response.body() as Employee
            item = listOf(nonNullEmployee)
        }

        recycler.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = EverythingAdapter(item)
        recycler.layoutManager = manager
        recycler.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        var apiInterface: ApiInterface = Factory.create()
        apiInterface.addEmployee(Employee("David", 7, 30, "Intern")).enqueue(this)
    }
}
