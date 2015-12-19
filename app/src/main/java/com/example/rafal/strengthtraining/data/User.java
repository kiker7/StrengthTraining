package com.example.rafal.strengthtraining.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Kiker on 18.12.15.
 * Strength Training
 */
public class User implements Serializable {


    @DatabaseField(generatedId = true, columnName = "user_id")
    public int userId;

    @DatabaseField(columnName = "user_name")
    public String userName;

    @DatabaseField(columnName = "macro_type")
    public int macroType;

    public User() {
    }

    public User(final String userName, final int macroType){
        this.userName = userName;
        this.macroType = macroType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", macroType=" + macroType +
                '}';
    }
}
