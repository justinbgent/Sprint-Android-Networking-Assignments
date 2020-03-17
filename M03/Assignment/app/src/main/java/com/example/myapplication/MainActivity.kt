package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener {
            startActivity(Intent(this, GetPickerActivity::class.java))
        }

        btn_post.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }

        btn_put.setOnClickListener {
            startActivity(Intent(this, PutActivity::class.java))
        }

        btn_delete.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
        }
    }
}
