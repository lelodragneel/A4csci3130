package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Business implements Serializable {

    private String id;
    private int number;
    private String name;
    private String address;
    private String province;
    private String type;

    public Business() {

    }

    public Business(String id, int number, String name, String address, String province,
                    String type){
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.province = province;
        this.type = type;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("number", number);
        result.put("name", name);
        result.put("address", address);
        result.put("province", province);
        result.put("type", type);

        return result;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getProvince() {
        return province;
    }

    public String getType() {
        return type;
    }
}
