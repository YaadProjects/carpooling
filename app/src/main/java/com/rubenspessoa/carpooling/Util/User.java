package com.rubenspessoa.carpooling.Util;

/**
 * Created by rubenspessoa on 27/09/16.
 */
public class User {



    private int ridesCount;
    private String name;
    private String email;
    private boolean owe;

    public User(){}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.ridesCount = 0;
        this.owe = false;
    }

    public void addRide() {
        this.ridesCount++;
        if (this.ridesCount > 0) {
            this.owe = true;
        }
    }

    public void decreaseRide() {
        if (this.ridesCount > 0) {
            this.ridesCount--;
        } else {
            this.owe = false;
        }
    }

    public String getName(){
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public boolean owe() {
        return owe;
    }

    public int getRidesCount() {
        return ridesCount;
    }
}
