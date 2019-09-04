package com.example.oceaniacountries.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oceaniacountries.R
import com.example.oceaniacountries.model.OceaniaCountry
import kotlinx.android.synthetic.main.recycler_list_item.view.*

class RecyclerViewAdapter(private val countriesInfo: MutableList<OceaniaCountry>?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = countriesInfo?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (countriesInfo != null){
            val country = countriesInfo[position]
            holder.countryName.text = country.name
            holder.countryCapital.text = country.capital
            holder.countryCode.text = country.alpha2Code
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val countryName: TextView = view.country_name
        val countryCapital: TextView = view.country_capital
        val countryCode: TextView = view.country_alpha_code
    }
}