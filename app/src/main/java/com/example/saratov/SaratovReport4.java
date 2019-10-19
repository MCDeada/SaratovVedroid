package com.example.saratov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport4 extends AppCompatActivity {

    private ComplaintDraft complaint;
    private TextView warningTextView;
    private CheckBox BoxAnon, BoxNotAnon;
    private boolean isAnon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_4);

        complaint = (ComplaintDraft) getIntent().getSerializableExtra("complaint");

        warningTextView = findViewById(R.id.txtViewWarning);
        BoxAnon = findViewById(R.id.boxAnon);

        BoxNotAnon = findViewById(R.id.boxNotAnon);
        BoxNotAnon.setChecked(true);

        warningTextView.setText(R.string.NotAnonTypeDescription);

        BoxAnon.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.boxAnon:
                        if (BoxAnon.isChecked()) {
                            isAnon = true;
                            BoxNotAnon.setChecked(false);
                            warningTextView.setText(R.string.AnonTypeDescription);
                        }
                        break;
                }
            }
        });
        BoxNotAnon.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.boxNotAnon:
                        if (BoxNotAnon.isChecked()) {
                            isAnon = false;
                            BoxAnon.setChecked(false);
                            warningTextView.setText(R.string.NotAnonTypeDescription);
                        }
                        break;
                }
            }
        });
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
                if (!BoxNotAnon.isChecked() && !BoxAnon.isChecked()) {
                    warningTextView.setError("Отметьте один из вариантов.");
                    return false;
                }
                // complaint.setAnonymous(isAnon);
                if (isAnon) {
                    try {
                        /*DefaultApi api = new DefaultApi();
                        api.updateomplaintDraft(complaint);*/
                        Intent intent = new Intent(this, SaratovReport6.class);
                        //intent.putExtra("complaint", complaint);
                        startActivity(intent);
                    } /*catch (ApiException e) {
                        txtView.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    } */ catch (RuntimeException re) {
                        warningTextView.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                    }
                } else {
                    //DefaultApi api = new DefaultApi();
                    try {
                        //api.updateomplaintDraft(complaint);
                        Intent intent = new Intent(this, SaratovReport5.class);
                        //intent.putExtra("complaint", complaint);
                        startActivity(intent);
                    } /*catch (ApiException e) {
                        txtView.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                        return false;
                    } */ catch (RuntimeException re) {
                        warningTextView.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                        return false;
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ClearAllErrors() {
        warningTextView.setError(null);
    }
}
