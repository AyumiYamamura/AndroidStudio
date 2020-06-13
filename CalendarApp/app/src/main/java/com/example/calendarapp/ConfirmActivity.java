package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ConfirmActivity extends AppCompatActivity {

    //ReserveOpenHelperクラスを定義する
    ReserveOpenHelper reserveOpenHelper = null;
    String re_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);



        // データベースから値を取得する
        if(reserveOpenHelper == null){
            reserveOpenHelper = new ReserveOpenHelper(ConfirmActivity.this);
        }

        //登録用予約年月日の取得
        re_date = getIntent().getStringExtra("RESERVATION");

        //予約日時、入力内容の取得
        String selectedDate = getIntent().getStringExtra("DATE");
        String reservationTime = getIntent().getStringExtra("TIME");
        String name = getIntent().getStringExtra("NAME");
        String phone = getIntent().getStringExtra("PHONE");
        String email = getIntent().getStringExtra("EMAIL");

        // 予約日時、入力内容をTextViewに表示する
        TextView cDateLabel = findViewById(R.id.cDate);
        TextView cTimeLabel = findViewById(R.id.cTime);
        TextView cNameLabel = findViewById(R.id.cName);
        TextView cPhoneLabel = findViewById(R.id.cPhone);
        TextView cMailLabel = findViewById(R.id.cEmail);

        cDateLabel.setText(re_date.substring(0, 4) + "年" +selectedDate);
        cTimeLabel.setText(reservationTime + "～");
        cNameLabel.setText(name + " 様");
        cPhoneLabel.setText("電話番号： " + phone);
        cMailLabel.setText("メールアドレス： " + email);


        /**
         * 予約するボタンの処理
         */
        // idがgoCompleteBtnのボタンを取得
        Button goCompleteButton = (Button) findViewById(R.id.goCompleteBtn);
        // clickイベント追加
        goCompleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //予約日時、入力内容の取得
                String selectedDate = getIntent().getStringExtra("DATE");
                String reservationTime = getIntent().getStringExtra("TIME");
                String name = getIntent().getStringExtra("NAME");
                String phone = getIntent().getStringExtra("PHONE");
                String email = getIntent().getStringExtra("EMAIL");

                // データベースを取得
                SQLiteDatabase db = reserveOpenHelper.getWritableDatabase();


                //現在日時をyyyy/MM/dd HH:mm:ss形式で取得する
                final DateFormat redf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                final Date r_now = new Date(System.currentTimeMillis());


                //新規予約のレコードを作成
                try {

                    db.execSQL("insert into RESERVATION_TABLE(name,r_date,r_time,phone,mail,send_flag,delete_flag,created_date,deleted_date) VALUES('" +  name + "', '" + re_date + "', '" + reservationTime + "', '" + phone + "', '" + email + "', 0,0,'" + redf.format(r_now) + "', '" + redf.format(r_now) + "')");
                } catch (SQLException e) {
                    Log.e("ERROR", e.toString());
                }finally {
                    db.close();
                }

                // 完了画面(CompleteActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), CompleteActivity.class);

                intent.putExtra("NAME",name);
                intent.putExtra("PHONE",phone);
                intent.putExtra("EMAIL",email);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",reservationTime);
                intent.putExtra("RESERVATION",re_date);

                startActivity(intent);
            }
        });

        /**
         * 修正するボタンの処理
         */
        // idがcorrectBtnのボタンを取得
        Button correctButton = (Button) findViewById(R.id.correctBtn);
        // clickイベント追加
        correctButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //予約日時、入力内容の取得
               // String selectedDate = getIntent().getStringExtra("DATE");
               // String reservationTime = getIntent().getStringExtra("TIME");
                String name = getIntent().getStringExtra("NAME");
                String phone = getIntent().getStringExtra("PHONE");
                String email = getIntent().getStringExtra("EMAIL");

                // 個人情報入力画面(PersonalInformationActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), PersonalInformationActivity.class);

                intent.putExtra("NAME",name);
                intent.putExtra("PHONE",phone);
                intent.putExtra("EMAIL",email);

                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }



}
