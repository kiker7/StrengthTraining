package com.example.rafal.strengthtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.DatabaseInitializer;
import com.example.rafal.strengthtraining.data.ExerciseDAO;
import com.example.rafal.strengthtraining.data.Repo;
import com.example.rafal.strengthtraining.models.Exercise;
import com.example.rafal.strengthtraining.models.User;
import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button atlasBtn;
    TextView textViewDB;
    DatabaseInitializer dbInit;
    DatabaseHelper dbHelp;
    Dao<Exercise,Integer> exerciseDAO;
    ExerciseDAO exerciseDAO2;
    Exercise exe;
    List<Exercise> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlasBtn = (Button) findViewById(R.id.button2);
        textViewDB = (TextView) findViewById(R.id.textViewDB);

        // Test dla DB

        Repo repo = new Repo(this);

//        exe = new Exercise();
//
//        exe = repo.Exercises.getExerciseByIdentifier(5,5);
//
//        if(exe == null){
//            textViewDB.setText("Jebany NULLL");
//        }else{
//            textViewDB.setText(exe.getName());
//        }

        User user;

        list = repo.Exercises.getAll();
        textViewDB.setText(String.valueOf(list.size()));
//        user.delete(repo);

    }



}
