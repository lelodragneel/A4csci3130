package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText editText_number, editText_name, editText_address;
    private Spinner spinner_province, spinner_type;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business) getIntent().getSerializableExtra("Business");

        appState = ((MyApplicationData) getApplicationContext());

        editText_number = (EditText) findViewById(R.id.editText_number);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_address = (EditText) findViewById(R.id.editText_address);
        spinner_province = (Spinner) findViewById(R.id.spinner_province);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);

        if(receivedBusinessInfo != null){
            editText_number.setText(receivedBusinessInfo.getNumber() + "");
            editText_name.setText(receivedBusinessInfo.getName());
            editText_address.setText(receivedBusinessInfo.getAddress());

            spinner_province.setSelection(getSpinnerIndexOf(spinner_province, receivedBusinessInfo.
                    getProvince()));

            spinner_type.setSelection(getSpinnerIndexOf(spinner_type, receivedBusinessInfo.
                    getType()));
        }
    }

    public int getSpinnerIndexOf(Spinner spinner, String item) {
        Adapter spinAdapter = spinner.getAdapter();
        for (int i = 0; i < spinAdapter.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(item)) {
                return i;
            }
        }
        return 0;
    }

    public void updateContact(View v) {
        int number = Integer.parseInt(editText_number.getText().toString());
        String name = editText_name.getText().toString();
        String address = editText_address.getText().toString();
        String province = spinner_province.getSelectedItem().toString();
        String type = spinner_type.getSelectedItem().toString();

        Business business = new Business(receivedBusinessInfo.getId(), number, name, address,
                province, type);

        appState.firebaseReference.child(receivedBusinessInfo.getId()).setValue(business);
    }

    public void eraseContact(View v) {
        appState.firebaseReference.child(receivedBusinessInfo.getId()).removeValue();
    }
}
