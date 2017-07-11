package com.verbosetech.fooddude.Models;

/**
 * Created by sagar on 29/6/17.
 */

public class Profile {

    String add_type;
    String address;

    public Profile(String add_type, String address) {
        this.add_type = add_type;
        this.address = address;
    }

    public String getAdd_type() {
        return add_type;
    }

    public void setAdd_type(String add_type) {
        this.add_type = add_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
