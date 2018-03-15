package com.acme.a3csci3130;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Lelo on 3/14/2018.
 */
public class CRUDTest {

    private FirebaseOperations operations;

    @Before
    public void setUp() throws Exception {
        operations = new FirebaseOperations();
    }

    @Test
    public void addToDatabase() throws Exception {

        Business business = new Business("id1", 999999999, "name1",
                "address1","NS", "fisher");
        operations.createBusiness(business);

        // check if it was written
        Business b = operations.readBusiness("id1");
        assertEquals(business, b);
    }

    @Test
    public void deleteFromDatabase() throws Exception {

        // assuming the addToDatabase test works, create it first
        Business business = new Business("id2", 222222222, "name2",
                "address2","NS", "fisher");
        operations.createBusiness(business);

        // then delete it
        operations.deleteBusiness("id2");

        // check if it was deleted anymore
        Business b = operations.readBusiness("id2");
        assertNull(b);
    }

    @Test
    public void updateFromDatabase() throws Exception {

        // assuming the addToDatabase test works, create it first
        Business business = new Business("id3", 222222222, "oldname",
                "address3","NS", "fisher");
        operations.createBusiness(business);

        // make a new updated business and update it in database
        Business business2 = new Business("id3", 222222222, "newname",
                "address3","NS", "fisher");
        operations.updateBusiness(business2);

        // check if it was updated
        Business b = operations.readBusiness("id3");
        assertEquals("newname", b.getName());
    }

}