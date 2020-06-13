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

/**
 * 個人情報入力画面
 */
public class PersonalInformationActivity extends AppCompatActivity {

    private EditText name;
    private EditText phoneNumber;
    private EditText email;

    //リクエストコードを設定
    static final int RESULT_CONFIRM = 1000;

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

                //登録用予約年月日の取得
                String re_date = getIntent().getStringExtra("RESERVATION");

                //フォーム入力内容の取得
                name = (EditText) findViewById(R.id.editText1);
                phoneNumber = (EditText) findViewById(R.id.editText2);
                email = (EditText) findViewById(R.id.editText3);

                String text1 = name.getText().toString();
                String text2 = phoneNumber.getText().toString();
                String text3 = email.getText().toString();

                //必須入力チェック
                if(text1.isEmpty()) {
                    name.setError("名前が入力されていません");
                    return;
                }
                if(text2.isEmpty()) {
                    phoneNumber.setError("電話番号が入力されていません");
                    return;
                }
                if(text3.isEmpty()) {
                    email.setError("メールアドレスが入力されていません");
                    return;
                }

                // 確認画面(ConfirmActivity)へ遷移
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);

                intent.putExtra("NAME",text1);
                intent.putExtra("PHONE",text2);
                intent.putExtra("EMAIL",text3);
                intent.putExtra("DATE",selectedDate);
                intent.putExtra("TIME",reservationTime);
                intent.putExtra("RESERVATION",re_date);

                startActivityForResult(intent,RESULT_CONFIRM);
            }
        });

        /**
         * 戻るボタンの処理
         */
        // idがgoBackBtn2のボタンを取得
        Button backButton2 = (Button) findViewById(R.id.goBackBtn2);
        // clickイベント追加
        backButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 時間選択画面(TimeActivity)へ遷移
                finish();
            }
        });

    }

    /**
     * 確認画面(ConfirmActivity)で修正ボタンが押されたときの処理
     */
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK && requestCode == RESULT_CONFIRM &&
                null != intent) {

            // ConfirmActivity からの返しの結果を受け取る
            String resName = intent.getStringExtra("NAME");
            String resPhone = intent.getStringExtra("PHONE");
            String resMail = intent.getStringExtra("EMAIL");

            //受け取った値をEditViewにセット
            name.setText(resName);
            phoneNumber.setText(resPhone);
            email.setText(resMail);
        }
    }

}
