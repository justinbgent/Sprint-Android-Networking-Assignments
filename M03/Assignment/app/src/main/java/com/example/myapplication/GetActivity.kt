package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.apapters.EverythingAdapter

class GetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
    }

//    val oceaniaCountry = response.body()
//    recycler.setHasFixedSize(true)
//    val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//    val adapter = EverythingAdapter(oceaniaCountry)
//    recycler.layoutManager = manager
//    recycler.adapter = adapter
}
