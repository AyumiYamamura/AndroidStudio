package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());

        calendarGridView.setOnItemClickListener(this);
    }

    /**
     * カレンダーセルクリック時の処理
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Log.d("position",String.valueOf(position));
        Log.d("date", mCalendarAdapter.getItem(position).toString());

        //予約日のフォーマットを"MM月dd日"に直す
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日", Locale.US);
        String currentDateString = dateFormat.format(mCalendarAdapter.getItem(position));

        // 時間選択画面（TimeActivity）へ遷移
        Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
        intent.putExtra("DATE",currentDateString);
        startActivity(intent);

    }
}