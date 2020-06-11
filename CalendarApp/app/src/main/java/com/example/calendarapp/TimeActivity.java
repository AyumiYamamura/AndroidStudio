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

/**
 * 時間選択画面
 */
public class TimeActivity extends AppCompatActivity {
    private RadioGroup timeGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //予約日の取得
        String selectedDate = getIntent().getStringExtra("DATE");

        //予約日が1～9日の時は頭の0を取り除く
        String dStr = selectedDate.substring(3,5);
        if(dStr.equals("01") || dStr.equals("02") || dStr.equals("03")  || dStr.equals("04")  || dStr.equals("05")  || dStr.equals("06")  || dStr.equals("07")  || dStr.equals("08")  || dStr.equals("09")) {
            String dRemoved = selectedDate.substring(0,3)+ selectedDate.substring(4,9);
            selectedDate = dRemoved;
        }

        //予約月が10,11,12月以外の時は頭の0を取り除く
        String mStr = selectedDate.substring(0,2);
        if(!(mStr.equals("10")) && !(mStr.equals("11")) && !(mStr.equals("12"))) {
            String mRemoved = selectedDate.replaceFirst("0", "");
            selectedDate = mRemoved;
        }else{

        }

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
                TextView txt1 = (TextView) findViewById(R.id.dateLabel);
                String selectedDate = txt1.getText().toString();

                // 個人情報入力画面(PersonalInformationActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), PersonalInformationActivity.class);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",timeButton.getText());

                startActivity(intent);
            }
        });

        /**
         * 戻るボタンの処理
         */
        // idがgoNextBtnのボタンを取得
        Button backButton1 = (Button) findViewById(R.id.goBackBtn1);
        // clickイベント追加
        backButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 日付選択画面(MainActivity)へ遷移
                finish();
            }
        });
    }



}
