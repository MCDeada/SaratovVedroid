package com.example.saratov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SaratovMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sarstov_menu);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.menuSettingsLayout:
                 intent = new Intent(this, SaratovSettings.class);
                startActivity(intent);
                break;
            case R.id.menuContactLayout:
                intent = new Intent(this, SaratovContacts.class);
                startActivity(intent);
                break;
            case R.id.menuFaqLayout:
                 intent = new Intent(this, SaratovFaq.class);
                startActivity(intent);
                break;
            case R.id.menuFeedbackLayout:
                break;
        }

    }
}
