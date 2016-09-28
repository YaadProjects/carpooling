package com.rubenspessoa.carpooling.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubenspessoa on 28/09/16.
 */
public class Manager {

    static List<User> users;

    public Manager() {
        users = new ArrayList<User>();
    }

    public Manager(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public static User getUser(int index) {
        return users.get(index);
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<String>();

        for (User user: this.users) {
            names.add(user.name);
        }

        return names;
    }

    public List<User> getUsers() {
        return this.users;
    }


}
