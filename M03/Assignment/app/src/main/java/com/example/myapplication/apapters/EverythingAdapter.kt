package com.example.myapplication.apapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Employee
import kotlinx.android.synthetic.main.recycler_item.view.*

class EverythingAdapter(private val employees: List<Employee>?): RecyclerView.Adapter<EverythingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = employees?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (employees != null){
            val employee = employees[position]
            holder.name.text = employee.name
            holder.title.text = employee.title
            holder.age.text = employee.age.toString()
            holder.id.text = employee.id.toString()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.txt_name
        val title: TextView = view.txt_title
        val age: TextView = view.txt_age
        val id: TextView = view.txt_id
    }
}