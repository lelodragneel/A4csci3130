package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateBusinessActivity extends Activity {

    private EditText editText_number, editText_name, editText_address;
    private Spinner spinner_province, spinner_type;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        editText_number = (EditText) findViewById(R.id.editText_number);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_address = (EditText) findViewById(R.id.editText_address);
        spinner_province = (Spinner) findViewById(R.id.spinner_province);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String id = appState.firebaseReference.push().getKey();
        int number = Integer.parseInt(editText_number.getText().toString());
        String name = editText_name.getText().toString();
        String address = editText_address.getText().toString();
        String province = spinner_province.getSelectedItem().toString();
        String type = spinner_type.getSelectedItem().toString();

        Business business = new Business(id, number, name, address, province, type);

        appState.firebaseReference.child(id).setValue(business);

        finish();
    }
}
