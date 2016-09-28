package com.rubenspessoa.carpooling.Util;

/**
 * Created by rubenspessoa on 27/09/16.
 */
public class User {

    public int rideCount;
    public String name;
    public boolean owe;

    public User(String name) {
        this.name = name;
        this.rideCount = 0;
        this.owe = false;
    }

    public void addRide() {
        this.rideCount++;
        if (this.rideCount > 0) {
            this.owe = true;
        }
    }

    public void decreaseRide() {
        if (this.rideCount > 0) {
            this.rideCount--;
        } else {
            this.owe = false;
        }
    }


}
