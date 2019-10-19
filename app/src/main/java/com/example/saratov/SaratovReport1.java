package com.example.saratov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport1 extends AppCompatActivity {

    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_1);
        title = findViewById(R.id.Theme);
        description = findViewById(R.id.description);
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
        // Handle presses on the action bar items+
        ClearAllErrors();
        switch (item.getItemId()) {
            case R.id.action_next:
                try {
                    DefaultApi API = new DefaultApi();
                    if (title.getText().toString().isEmpty()) {
                        title.setError("Введите тему:");
                        return false;
                    }
                    /*ComplaintDraft complaintDraft = API.createComplaintDraft(UserInfo.userId);
                    complaintDraft.setTitle(title.getText().toString());
                    complaintDraft.setDescription(description.getText().toString());
                    API.updateomplaintDraft(complaintDraft);*/
                    Intent intent = new Intent(this, SaratovReport2.class);
                    //intent.putExtra("complaint", complaintDraft);
                    startActivity(intent);
                    return true;
                /*} catch (ApiException e) {
                    description.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    return false;*/
                } catch (RuntimeException re) {
                    description.setError("Не удаётся подключится к серверу. Код ошибки: " + re.getMessage());
                    return false;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ClearAllErrors(){
        title.setError(null);
        description.setError(null);
    }
}
