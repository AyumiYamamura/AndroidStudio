package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        //予約日時、入力内容の取得
        String selectedDate = getIntent().getStringExtra("DATE");
        String reservationTime = getIntent().getStringExtra("TIME");
        String name = getIntent().getStringExtra("NAME");
        String phone = getIntent().getStringExtra("PHONE");
        String email = getIntent().getStringExtra("EMAIL");

        // 予約日時、入力内容をTextViewに表示する
        TextView cpDateLabel = findViewById(R.id.cpDate);
        TextView cpTimeLabel = findViewById(R.id.cpTime);
        TextView cpNameLabel = findViewById(R.id.cpName);
        TextView cpPhoneLabel = findViewById(R.id.cpPhone);
        TextView cpMailLabel = findViewById(R.id.cpEmail);

        //登録用予約年月日の取得
        String re_date = getIntent().getStringExtra("RESERVATION");


        cpDateLabel.setText(re_date.substring(0, 4) + "年" + selectedDate);
        cpTimeLabel.setText(reservationTime + "～");
        cpNameLabel.setText(name + " 様");
        cpPhoneLabel.setText("電話番号： " + phone);
        cpMailLabel.setText("メールアドレス： " + email);
    }
}
