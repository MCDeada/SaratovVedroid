package com.example.saratov;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Complaint;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport7 extends AppCompatActivity {

    private ComplaintDraft complaint;

    private ImageButton nextPage;

    private TextView titleTxt;
    private TextView descripTxt;
    private TextView typeTxt;
    private TextView dangerTxt;
    private TextView nameTxt;
    private TextView emailTxt;
    private TextView phoneTxt;
    private TextView adressTxt;
    private TextView dateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_7);
        complaint = (ComplaintDraft)getIntent().getSerializableExtra("complaint");

        titleTxt = findViewById(R.id.txtSubjectInfoCheck);
        descripTxt = findViewById(R.id.txtDescriptionInfoCheck);
        typeTxt = findViewById(R.id.txtTypeInfoCheck);
        dangerTxt = findViewById(R.id.txtDangerInfoCheck);
        nameTxt = findViewById(R.id.txtNameInfoCheck);
        emailTxt = findViewById(R.id.txtMailInfoCheck);
        phoneTxt = findViewById(R.id.txtPhoneInfoCheck);
        adressTxt = findViewById(R.id.txtAdressInfoCheck);
        dateTxt = findViewById(R.id.txtDataInfoCheck);

        titleTxt.setText(complaint.getTitle());
        descripTxt.setText(complaint.getDescription());
        typeTxt.setText(complaint.getCategory().getCategory());
        dangerTxt.setText(String.valueOf(complaint.getDangerLevel()));
        nameTxt.setText(complaint.getPerson().getName());
        emailTxt.setText(complaint.getPerson().getEmail());
        phoneTxt.setText(complaint.getPerson().getPhone());
        adressTxt.setText("");
        dateTxt.setText("");

        addListenerOnButton();
    }

    public void addListenerOnButton() {

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
                DefaultApi api = new DefaultApi();
                try{
                    api.sendomplaint(complaint);
                    Intent intent = new Intent(this, SaratovMenu.class);
                    startActivity(intent);
                } catch (ApiException e) {
                    titleTxt.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    return false;
                } catch (RuntimeException re) {
                    titleTxt.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                    return false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ClearAllErrors(){
        titleTxt.setError(null);
    }
}
