package com.lambdaschool.httpoperations

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpDeleteActivity : AppCompatActivity(), Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if(response.isSuccessful){
            Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "DeleteFailed", Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Delete Request: Delete existing employee with id 1"

        employeesService = JsonPlaceHolderApi.Factory.create()
        deleteEmployee()
    }

    private fun deleteEmployee(){
        // TODO: delete the employee
        employeesService.deleteEmployeeById("1").enqueue(this)
    }
}
