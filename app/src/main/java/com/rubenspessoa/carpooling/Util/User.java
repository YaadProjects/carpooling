package com.rubenspessoa.carpooling.Util;

/**
 * Created by rubenspessoa on 27/09/16.
 */
public class User {

    public int ridesCount;
    public String name;
    public boolean owe;

    public User(String name) {
        this.name = name;
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


}
