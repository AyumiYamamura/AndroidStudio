package com.example.calendarapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReserveOpenHelper extends SQLiteOpenHelper {
    // データベース自体の名前
    static final private String DBName = "RESERVATION_DB";
    // データベースのバージョン
    static final private int VERSION = 1;

    // コンストラクタ　
    public ReserveOpenHelper(Context context){
        super(context, DBName, null, VERSION);
    }

    // データベースが作成された時に実行される処理
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * テーブル(RESERVATION_TABLE)を作成する
         * 引数で指定されているカラムは以下の通り
         *
         * 予約番号 ・・・ id：列名 , INTEGER：数値型 , PRIMATY KEY：テーブル内の行で重複無し , AUTOINCREMENT：1から順番に振っていく
         * 氏名 ・・・ name：列名 , TEXT：文字列型
         * 予約年月日 ・・・ r_date：列名 , TEXT：文字列型
         * 予約時間 ・・・ r_time：列名 , TEXT：文字列型
         * 電話番号 ・・・ phone：列名 , TEXT：文字列型
         * メールアドレス ・・・ mail：列名 , TEXT：文字列型
         * メール送信済みフラグ ・・・ send_flag：列名 , TEXT：文字列型
         * 削除フラグ ・・・ delete_flag：列名 , TEXT：文字列型
         * 作成日時 ・・・ created_date：列名 , TEXT：文字列型
         * 削除日時 ・・・ deleted_date：列名 , TEXT：文字列型
         */
        db.execSQL("CREATE TABLE RESERVATION_TABLE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name, " +
                "r_date, " +
                "r_time, " +
                "phone, " +
                "mail, " +
                "send_flag, " +
                "delete_flag, " +
                "created_date, " +
                "deleted_date)");
    }

    // データベースをバージョンアップした時に実行される処理
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS RESERVATION_TABLE");
        // 新しくテーブルを作成する
        onCreate(db);
    }

    // データベースが開かれた時に実行される処理
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}

