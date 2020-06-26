package com.example.memokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_create_kotlin_memo.*

class MainActivity : AppCompatActivity() {// MemoOpenHelperクラスを定義
internal var helper: KtOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // データベースから値を取得する
        if (helper == null) {
            helper = KtOpenHelper(this@MainActivity)
        }
        // メモリストデータを格納する変数
        val memoList = ArrayList<HashMap<String, String>>()
        // データベースを取得する
        val db = helper!!.writableDatabase
        try {
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            val c = db.rawQuery("select uuid, body from KT_MEMO_TABLE order by id", null)
            // Cursorの先頭行があるかどうか確認
            var next = c.moveToFirst()

            // 取得した全ての行を取得
            while (next) {
                val data = HashMap<String, String>()
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                val uuid = c.getString(0)
                var body = c.getString(1)
                if (body.length > 10) {
                    // リストに表示するのは10文字まで
                    body = body.substring(0, 11) + "..."
                }
                // 引数には、(名前,実際の値)という組合せで指定します　名前はSimpleAdapterの引数で使用します
                data["body"] = body
                data["id"] = uuid
                memoList.add(data)
                // 次の行が存在するか確認
                next = c.moveToNext()
            }
        } finally {
            // finallyは、tryの中で例外が発生した時でも必ず実行される
            // dbを開いたら確実にclose
            db.close()
        }

        // Adapter生成
        val simpleAdapter = SimpleAdapter(this,
            memoList, // 使用するデータ
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
           //TextView idTextView = (TextView)two.findViewById(android.R.id.text2);
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