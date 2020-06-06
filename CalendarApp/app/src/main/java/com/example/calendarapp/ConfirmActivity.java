package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

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

        cDateLabel.setText(selectedDate);
        cTimeLabel.setText(reservationTime + "～");
        cNameLabel.setText(name);
        cPhoneLabel.setText(phone);
        cMailLabel.setText(email);
    }
}
