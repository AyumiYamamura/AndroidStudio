package com.example.calendarapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private List<Date> dateArray = new ArrayList();
    private Context mContext;
    private DateManager mDateManager;
    private LayoutInflater mLayoutInflater;

    //カスタムセルを拡張したらここでWigetを定義
    private  static  class  ViewHolder {
        public TextView dateText;
    }

    public CalendarAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDateManager = new DateManager();
        dateArray = mDateManager.getDays();
    }

    @Override
    public int getCount(){
        return dateArray.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.calendar_cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        //セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp, (parent.getHeight() - (int)dp * mDateManager.getWeeks() ) / mDateManager.getWeeks());
        convertView.setLayoutParams(params);

        //日付のみ表示させる
        SimpleDateFormat dateFormat = new SimpleDateFormat("d",Locale.US);
        holder.dateText.setText(dateFormat.format(dateArray.get(position)));

        //当月以外のセル(背景色）をグレーアウト
        Date compA = dateArray.get(position);
        Date compB = new Date();
        if(mDateManager.isCurrentMonth(compA)){
            convertView.setBackgroundColor(Color.WHITE);
        }else{
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grayColor));
        }



        //過去日のセル(背景色）をグレーアウト
        if(compA.before(compB)){
        convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grayColor));
        }

        //当日のセル(背景色）を赤色に
        if (mDateManager.isToday(dateArray.get(position))) {
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pinkColor));
        }

        //日曜日を赤、土曜日を青に
        int colorId;
        switch (mDateManager.getDayOfWeek(dateArray.get(position))){
            case 1:
                colorId = Color.RED;
                break;

            case 2:
                colorId = Color.BLUE;
                break;

            default:
                colorId =Color.BLACK;
                break;
        }
        holder.dateText.setTextColor(colorId);

        return convertView;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public Object getItem(int position){
            return dateArray.get(position);
        }

    //表示月を取得
    public String getTitle(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM",Locale.US);
        return format.format(mDateManager.mCalendar.getTime());
    }

    //翌月表示
    public void nextMonth(){
        mDateManager.nextMonth();
        dateArray = mDateManager.getDays();
        //更新
        this.notifyDataSetChanged();
    }

    //前月表示
    public void prevMonth(){
        mDateManager.prevMonth();
        dateArray = mDateManager.getDays();
        //更新
        this.notifyDataSetChanged();
    }
}
