package com.xuan.waitgamedemo.model;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by axuan on 2016/12/24.
 */

public class User {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static List<User> generateRandomUsers() {
        List<User> users = new ArrayList<>();
        int random = new Random().nextInt(100) + 10;
        for (int i = 0; i < random; i++) {
            users.add(new User("jack_" + SystemClock.elapsedRealtime(), "age_" + new Random().nextInt(40) + 10));
        }
        return users;
    }
}
