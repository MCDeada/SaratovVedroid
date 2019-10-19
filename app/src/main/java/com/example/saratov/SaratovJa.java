package com.example.saratov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class SaratovJa extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_start);



}
    public void onClickCitizen(View view) {
        UserInfo.userId = Long.valueOf(1);
        Intent intent = new Intent(this, SaratovMap.class);
        startActivity(intent);
    }

    public void onClickExecutor(View view) {
        UserInfo.userId = Long.valueOf(2);
        Intent intent = new Intent(this, SaratovMap.class);
        startActivity(intent);
    }

    public void onClickHead(View view) {
        UserInfo.userId = Long.valueOf(3);
        Intent intent = new Intent(this, SaratovMap.class);
        startActivity(intent);
    }


}
