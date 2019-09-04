package com.example.oceaniacountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oceaniacountries.model.OceaniaCountry
import com.example.oceaniacountries.recycler.RecyclerViewAdapter
import com.example.oceaniacountries.retrofit.OceaniaCountriesRetriever
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<OceaniaCountry> {
    companion object{
        var countries = mutableListOf<OceaniaCountry>()
    }

    override fun onFailure(call: Call<OceaniaCountry>, t: Throwable) {
        t.printStackTrace()
        val response = "failure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = RecyclerViewAdapter(countries)
        recycler_list.layoutManager = manager
        recycler_list.adapter = adapter

        fetchCountriesButton.setOnClickListener {
            OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }

    override fun onResponse(call: Call<OceaniaCountry>, response: Response<OceaniaCountry>) {
        if(response.isSuccessful){
            val oceaniaCountry = response.body()
            countriesTextView.text = "${oceaniaCountry?.name}, ${oceaniaCountry?.capital}, ${oceaniaCountry?.alpha2Code}"
        }else{
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }
}
