package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


                // 完了画面(CompleteActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), CompleteActivity.class);

                intent.putExtra("NAME",name);
                intent.putExtra("PHONE",phone);
                intent.putExtra("EMAIL",email);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",reservationTime);

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
