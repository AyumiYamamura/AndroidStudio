package com.example.calendarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日付選択画面
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;
    private DateManager dateManager;

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

        // グレーアウトセル(カラーコード ： -2302756)をクリック時
        int bkColor = mCalendarAdapter.getBackground(view);
        if(bkColor == -2302756){
            // グレーアウトセルをクリック時のアラートダイアログを作成
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("選択された日は予約不可です" );
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(true);
            builder.show();
        }else {


            //予約日のフォーマットを"MM月dd日(E)"に直す
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日(E)", Locale.JAPANESE);
            String currentDateString = dateFormat.format(mCalendarAdapter.getItem(position));

            //登録用の予約年月日を取得
            SimpleDateFormat reservationFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE);
            String reservation = reservationFormat.format(mCalendarAdapter.getItem(position));

            // 時間選択画面（TimeActivity）へ遷移
            Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
            intent.putExtra("DATE", currentDateString);
            intent.putExtra("RESERVATION", reservation);

            startActivity(intent);
        }
    }
}