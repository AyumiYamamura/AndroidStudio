package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PersonalInformationActivity extends AppCompatActivity {

    //private AppCompatEditText name;
    //private AppCompatEditText phoneNumber;
    //private AppCompatEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        /**
         * 確認画面へボタンの処理
         */
        // idがgoConfirmBtnのボタンを取得
        Button goButton = (Button) findViewById(R.id.goConfirmBtn);
        // clickイベント追加
        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //予約日の取得
                String selectedDate = getIntent().getStringExtra("DATE");

                //予約時間の取得
                String reservationTime = getIntent().getStringExtra("TIME");
                //TextView dateLabel = findViewById(R.id.dateLabel);

                // 予約時間をTextViewに表示する
                // dateLabel.setText(selectedDate);

                //フォーム入力内容の取得
                EditText name = (EditText) findViewById(R.id.editText1);
                EditText phoneNumber = (EditText) findViewById(R.id.editText2);
                EditText email = (EditText) findViewById(R.id.editText3);

                String text1 = name.getText().toString();
                String text2 = phoneNumber.getText().toString();
                String text3 = email.getText().toString();

                // 確認画面(ConfirmActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);

                intent.putExtra("NAME",text1);
                intent.putExtra("PHONE",text2);
                intent.putExtra("EMAIL",text3);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",reservationTime);

                startActivity(intent);
            }
        });



    }
}
