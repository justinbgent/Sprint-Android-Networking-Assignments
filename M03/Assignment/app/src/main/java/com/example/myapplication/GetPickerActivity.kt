package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)

        btn_simple.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("key", "simple")
            startActivity(intent)
        }

        btn_path.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("key", "path")
            startActivity(intent)
        }

        btn_query.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("key", "query")
            startActivity(intent)
        }
    }
}
