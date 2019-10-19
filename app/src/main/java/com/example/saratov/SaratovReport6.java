package com.example.saratov;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport6 extends AppCompatActivity {

    private ComplaintDraft complaint;

    private TextView txtView;
    private CheckBox BoxPub, BoxNotPub;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_6);
        complaint = (ComplaintDraft) getIntent().getSerializableExtra("complaint");
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        txtView = findViewById(R.id.txtViewWarningPublicy);

        BoxPub.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.boxAnon:
                        if (BoxPub.isChecked()) {
                            isVisible = true;
                            BoxNotPub.setChecked(false);
                            txtView.setText(R.string.NotPubTypeDescription);
                        }
                        break;
                }
            }
        });
        BoxNotPub.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.boxNotAnon:
                        if (BoxNotPub.isChecked()) {
                            isVisible = false;
                            BoxPub.setChecked(false);
                            txtView.setText(R.string.PubTypeDescription);
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
        // Handle presses on the action bar items
        ClearAllErrors();
        switch (item.getItemId()) {
            case R.id.action_next:
                DefaultApi api = new DefaultApi();
                complaint.setVisible(isVisible);
                try {
                    api.updateomplaintDraft(complaint);
                    Intent intent = new Intent(this, SaratovReport7.class);
                    intent.putExtra("complaint", complaint);
                    startActivity(intent);
                } catch (ApiException e) {
                    txtView.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    return false;
                } catch (RuntimeException re) {
                    txtView.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                    return false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ClearAllErrors(){
        txtView.setError(null);
    }
}
