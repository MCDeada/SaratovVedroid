package com.example.saratov;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ComplaintDraft;
import io.swagger.client.model.Person;

public class SaratovReport5 extends AppCompatActivity {

    private ComplaintDraft complaint;

    private EditText txtName, txtEmail, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_5);
        complaint = (ComplaintDraft)getIntent().getSerializableExtra("complaint");
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        txtName = (EditText)findViewById(R.id.editTextName);
        txtEmail = (EditText)findViewById(R.id.editTextMail);
        txtPhone = (EditText)findViewById(R.id.editTextPhone);
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
                if (!CheckCorrection()){
                    return false;
                }
                Person person = complaint.getPerson();
                person.setName(txtName.toString());
                person.setEmail(txtEmail.toString());
                person.setPhone(txtPhone.toString());
                complaint.setPerson(person);
                try {
                api.updateomplaintDraft(complaint);
                    Intent intent = new Intent(this, SaratovReport6.class);
                    intent.putExtra("complaint", complaint);
                    startActivity(intent);
                } catch (ApiException e) {
                    txtName.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
                    return false;
                } catch (RuntimeException re) {
                    txtName.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
                    return false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean CheckCorrection(){
        boolean correction = true;
        if (txtName.getText().toString().isEmpty()){
            txtName.setError("Введите имя.");
            correction = false;
        }
        if (txtEmail.getText().toString().isEmpty()){
            txtEmail.setError("Введите email.");
            correction = false;
        }
        if (txtPhone.getText().toString().isEmpty()){
            txtPhone.setError("Введите номер телефона.");
            correction = false;
        }
        return correction;
    }

    private void ClearAllErrors(){
        txtName.setError(null);
        txtEmail.setError(null);
        txtPhone.setError(null);
    }
}
