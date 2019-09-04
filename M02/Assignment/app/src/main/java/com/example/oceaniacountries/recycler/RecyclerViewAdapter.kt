package com.example.oceaniacountries.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oceaniacountries.model.OceaniaCountry
import kotlinx.android.synthetic.main.recycler_list_item.view.*

class RecyclerViewAdapter(val countriesInfo: MutableList<OceaniaCountry>?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val country = view.country_name
        val countryCapital = view.country_capital
        val countryCode = view.country_alpha_code
    }
}