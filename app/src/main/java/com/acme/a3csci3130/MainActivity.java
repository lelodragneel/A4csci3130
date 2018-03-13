package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    private ListView listView_businesses;
    private FirebaseListAdapter<Business> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData) getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        listView_businesses = (ListView) findViewById(R.id.listView_businesses);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView businessName = (TextView) v.findViewById(android.R.id.text1);
                businessName.setText(model.getName());
            }
        };
        listView_businesses.setAdapter(firebaseAdapter);
        listView_businesses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business business = (Business) firebaseAdapter.getItem(position);
                showDetailView(business);
            }
        });
    }

    /**
     * Start the activity that lets you create a new business
     * This is called when New button is clicked
     *
     * @param v     The view passed by the button
     */
    public void newBusinessButton(View v) {
        Intent intent = new Intent(this, CreateBusinessActivity.class);
        startActivity(intent);
    }

    /**
     * Start the activity that lets you update or delete a business
     * This is called when a listview item is clicked
     *
     * @param business      The business object that was clicked from listview
     */
    private void showDetailView(Business business) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business", business);
        startActivity(intent);
    }

}
