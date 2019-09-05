package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.apapters.EverythingAdapter
import com.example.myapplication.model.Employee
import com.example.myapplication.retrofit.ApiInterface
import com.example.myapplication.retrofit.Factory
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity(), Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
        Toast.makeText(this, "Failed to Delete Employee", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        txt_msg.text = "Delete Successful"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        var apiInterface: ApiInterface = Factory.create()
        apiInterface.deleteEmployeeById("1").enqueue(this)
    }
}
