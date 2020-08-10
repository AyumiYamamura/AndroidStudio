package com.example.kotlintabswipe

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Tab3Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mainFrame = inflater!!.inflate(R.layout.flagment_tab3, container, false)
        val listView = mainFrame.findViewById(R.id.listViewMore) as ListView

        // ListView データ一覧の実装
        val article1 = Article("Article1", 4, R.drawable.ic_launcher1)
        val article2 = Article("Article2", 5, R.drawable.ic_launcher2)
        val article3 = Article("Article3", 6, R.drawable.ic_launcher3)
        val article4 = Article("Article4", 7, R.drawable.ic_launcher4)

        val mArticleList = arrayListOf(article1, article2, article3, article4)
        val mCustomAdapter = CustomAdapter(requireContext(), mArticleList)
        listView.adapter  = mCustomAdapter

        return mainFrame
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onDetach() {
        super.onDetach()
    }
    companion object {
        fun newInstance(): Tab3Fragment {
            val fragment = Tab3Fragment()
            return fragment
        }
    }


}




