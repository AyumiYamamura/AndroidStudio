package com.example.kotlintabswipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(context: Context, var mArticleList: List<Article>) : ArrayAdapter<Article>(context, 0, mArticleList) {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Articleの取得
        val article = mArticleList[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_items, parent, false)
        }

        // 各Viewの設定
        val imageView = view?.findViewById<ImageView>(R.id.image)
        imageView?.setImageResource(article.imageId)

        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = article.name

        val age = view?.findViewById<TextView>(R.id.month)
        age?.text = "${article.month} 月"

        return view!!
    }
}