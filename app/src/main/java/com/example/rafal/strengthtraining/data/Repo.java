package com.example.rafal.strengthtraining.data;

import android.content.Context;

/**
 * Created by Kiker on 13.12.15.
 */
public class Repo {

    DatabaseHelper db;

    public UserDAO Users;
    public ExerciseDAO Exercises;
    public MacrocycleDAO Macrocycles;

    public Repo(Context context){
        DatabseManager manager = new DatabseManager();
        db = manager.getHelper(context);

        Users = new UserDAO(db);
        Exercises = new ExerciseDAO(db);
        Macrocycles = new MacrocycleDAO(db);
    }
}
