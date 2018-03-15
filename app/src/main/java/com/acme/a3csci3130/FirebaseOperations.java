package com.acme.a3csci3130;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lelo on 3/14/2018.
 */

public class FirebaseOperations extends Application {

    private static List<Business> businesses;
    private DatabaseReference ref;

    /**
     * Constructor that asynchronously updates a list of businesses
     */
    protected FirebaseOperations() {
        ref = FirebaseDatabase.getInstance().getReference().child("businesses");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                businesses = new ArrayList<>();

                for (DataSnapshot business : dataSnapshot.getChildren()) {
                    businesses.add(business.getValue(Business.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    /**
     * Update a business in database with new values
     * Note: this uses the business's ID as reference
     *
     * @param business      The business object to update
     */
    public void updateBusiness(Business business) {
        String businessID = business.getId();
        ref.child(businessID).setValue(business);
    }

    /**
     * Deletes a business from database
     *
     * @param businessID        The id of the business object to delete
     */
    public void deleteBusiness(String businessID) {
        ref.child(businessID).removeValue();
    }

    /**
     * Creates a business in the database with a randomly generated ID
     *
     * @param business      The business object to create
     */
    public void createBusiness(Business business) {
        String businessID = business.getId();
        ref.child(businessID).setValue(business);
    }

    /**
     * Iterates through the list of businesses to find the business object with the desired id
     *
     * @param businessID    Business id of the business to be returned
     * @return              The business object associated with the given business id
     */
    public Business readBusiness(String businessID) {
        for (Business b : businesses) {
            if (b.getId().equals(businessID)) {
                return b;
            }
        }

        return null;
    }
}
