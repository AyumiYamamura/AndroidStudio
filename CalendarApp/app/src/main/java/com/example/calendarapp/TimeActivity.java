package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimeActivity extends AppCompatActivity {
    private RadioGroup timeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //予約日の取得
        String selectedDate = getIntent().getStringExtra("DATE");

        // 予約日をTextViewに表示する
        TextView dateLabel = findViewById(R.id.dateLabel);
        dateLabel.setText(selectedDate);

        // RadioGroupをメンバ変数に保存しておく
        timeGroup = (RadioGroup)findViewById(R.id.timeGroup);

    /**
     * 次へボタンの処理
     */
        // idがgoNextBtnのボタンを取得
        Button newButton = (Button) findViewById(R.id.goNextBtn);
        // clickイベント追加
        newButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 選択されたラジオボタンを取得
                int checkedId = timeGroup.getCheckedRadioButtonId();
                RadioButton timeButton = (RadioButton) findViewById(checkedId);

                //予約日の取得
                String selectedDate = getIntent().getStringExtra("DATE");

                // 個人情報入力画面(PersonalInformationActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), PersonalInformationActivity.class);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",timeButton.getText());
                startActivity(intent);
            }
        });

    }
}
