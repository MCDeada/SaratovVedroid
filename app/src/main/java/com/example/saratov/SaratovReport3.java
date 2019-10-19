package com.example.saratov;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport3 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    // UI
    private SeekBar seekBar;
    private TextView mTextView;

    // DATA
    private ComplaintDraft complaint;
    private DefaultApi API;

    // TODO: добавить отдельный класс для уровней опасности проблемы

    Integer dangerLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_3);
        complaint = (ComplaintDraft) getIntent().getSerializableExtra("complaint");
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        mTextView = findViewById(R.id.textView4);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (progress) {
            case 1:
            case 2:
            case 3:
            case 4:
                mTextView.setText("Проблема не представляет непосредственной опасности для жизни и здоровья людей.");
                dangerLevel = progress;
                break;
            case 5:
            case 6:
                mTextView.setText("Есть риск нанесения вреда здоровью или угрозы жизни людей.");
                dangerLevel = progress;
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                mTextView.setText("Проблема представляет непосредственную угрозу здоровью или угрозу жизни людей");
                dangerLevel = progress;
                break;
        }
    }

    // TODO:
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigator_report, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ClearAllErrors();
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_next:
                try {
                    //API = new DefaultApi();
                    //complaint.setDangerLevel(dangerLevel);
                    //api.updateomplaintDraft(complaint);
                    Intent intent = new Intent(this, SaratovReport4.class);
                    // intent.putExtra("complaint", complaint);
                    startActivity(intent);
                    return true;
                } /*catch (ApiException e) {
                    mTextView.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    return false;
                } */ catch (RuntimeException re) {
                    mTextView.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                    return false;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ClearAllErrors() {
        mTextView.setError(null);
    }
}
