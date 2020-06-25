package com.example.memokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_create_kotlin_memo.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ToDo:データベースから値を取得する
        // 仮のデータを作成
        val tmpList = ArrayList<HashMap<String, String>>()
        for (i in 1..5) {
            val data = HashMap<String, String>()
            // 引数には、(名前,実際の値)という組合せで指定します　名前はSimpleAdapterの引数で使用します
            data["body"] = "サンプルデータ$i"
            data["id"] = "sampleId$i"
            tmpList.add(data)
        }

        // Adapter生成
        // ToDo:tmpListを正式なデータと入れ替える
        val simpleAdapter = SimpleAdapter(this,
            tmpList, // 使用するデータ
            android.R.layout.simple_list_item_2, // 使用するレイアウト
            arrayOf("body", "id"), // どの項目を
            intArrayOf(android.R.id.text1, android.R.id.text2) // どのidの項目に入れるか
        )

        // idがmemoListのListViewを取得
        val listView = findViewById<View>(R.id.memoList) as ListView
        listView.adapter = simpleAdapter

        // リスト項目をクリックした時の処理
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            /**
             * @param parent ListView
             * @param view 選択した項目
             * @param position 選択した項目の添え字
             * @param id 選択した項目のID
             */
            // インテント作成  第二引数にはパッケージ名からの指定で、遷移先クラスを指定
            val intent = Intent(this@MainActivity, com.example.memokotlin.CreateKotlinMemo::class.java)

            // 選択されたビューを取得 TwoLineListItemを取得した後、text2の値を取得する
            val two = view as TwoLineListItem
            val idTextView = two.text2 as TextView
            val isStr = idTextView.text as String
            // 値を引き渡す (識別名, 値)の順番で指定します
            intent.putExtra("id", isStr)
            // Activity起動
            startActivity(intent)
        }


        /**
         * 新規作成するボタン処理
         */
        // idがnewButtonのボタンを取得
        val newButton = findViewById<View>(R.id.newButton) as Button
        // clickイベント追加
        newButton.setOnClickListener {
            // CreateMemoActivityへ遷移
            val intent = Intent(this@MainActivity, com.example.memokotlin.CreateKotlinMemo::class.java)
            intent.putExtra("id", "")
            startActivity(intent)
        }
    }
}