package com.example.memokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateKotlinMemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_kotlin_memo)


        // ListActivityからインテントを取得
        val intent = this.intent
        // 値を取得
        val id = intent.getStringExtra("id")
        // 画面に表示
        if (id == "") {
            // 新規作成の場合

        } else {
            // 編集の場合

        }

        /**
         * 登録ボタン処理
         */
        // idがregisterのボタンを取得
        val registerButton = findViewById<View>(R.id.register) as Button
        // clickイベント追加
        registerButton.setOnClickListener {
            // 入力内容を取得する
            val body = findViewById<View>(R.id.body) as EditText
            val bodyStr = body.text.toString()

            // ToDo:データベースに保存する

            // 保存後に一覧へ戻る
            val intent = Intent(this@CreateKotlinMemo,  com.example.memokotlin.MainActivity::class.java)
            startActivity(intent)
        }


        /**
         * 戻るボタン処理
         */
        // idがbackのボタンを取得
        val backButton = findViewById<View>(R.id.back) as Button
        // clickイベント追加
        backButton.setOnClickListener {
            // 保存せずに一覧へ戻る
            finish()
        }
    }

}