package com.example.memokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_create_kotlin_memo.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ToDo:データベースから値を取得する
        // 仮のデータを作成
        val tmplist = ArrayList<HashMap<String, String>>()
        for (i in 1..5) {
            val data = HashMap<String, String>()
            // 引数には、(名前,実際の値)という組合せで指定.　名前はSimpleAdapterの引数で使用
            data["body"] = "サンプルデータ$i"
            data["id"] = "sampleId$i"
            tmplist.add(data)
        }
        // Adapter生成
        // ToDo:tmpListを正式なデータと入れ替える
        val simpleAdapter = SimpleAdapter(this,
                tmplist,// 使用するデータ
                android.R.layout.simple_list_item_2, // 使用するレイアウト
                arrayOf("body", "id"), // どの項目を
                intArrayOf(android.R.id.text1, android.R.id.text2) // どのidの項目に入れるか
        )
        // idがmemoListのListViewを取得
        val listview =findViewById<View>(R.id.memoList) as ListView

    }
}