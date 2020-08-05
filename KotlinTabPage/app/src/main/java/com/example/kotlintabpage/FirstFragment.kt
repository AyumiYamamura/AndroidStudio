package com.example.kotlintabpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(){
    private var listView: ListView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val titles = arrayListOf("Mac", "Apple", "Mini", "iMac", "Pro")

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        this.listView = view.findViewById(R.id.listView) as ListView
       // val adapter = ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, titles)
      //  listView!!.adapter = adapter
        return view
    }
}