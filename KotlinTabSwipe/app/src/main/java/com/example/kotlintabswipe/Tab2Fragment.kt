package com.example.kotlintabswipe

import android.content.Context
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.flagment_tab2.*

class Tab2Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mainFrame = inflater!!.inflate(R.layout.flagment_tab2, container, false)
        val listView = mainFrame.findViewById(R.id.listView) as ListView
        val dataArray = arrayOf("News1", "News2", "News3", "News4")
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, dataArray)
        listView.adapter  = adapter
        return mainFrame
       // return inflater!!.inflate(R.layout.flagment_tab2, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onDetach() {
        super.onDetach()
    }
    companion object {
        fun newInstance(): Tab2Fragment {
            val fragment = Tab2Fragment()
            return fragment
        }
    }


}