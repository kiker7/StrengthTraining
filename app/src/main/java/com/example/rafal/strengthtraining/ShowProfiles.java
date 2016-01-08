package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ShowProfiles extends Activity {

    private DatabaseHelper databaseHelper = null;
    private User user;
    private Dao<User, Integer> userDao;
    private TextView currentName, macroType;
    private Button progressBtn, deleteBtn, changeMacroBtn;
    private int macroTypeFromUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_profiles);
        try {
            userDao = getHelper().getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final SharedPreferences userPrefs = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
        final String currentUserName = userPrefs.getString("userName","Mr Nobody");
        user = getByUserName(currentUserName);
        currentName = (TextView) findViewById(R.id.currentUserName);
        macroType = (TextView) findViewById(R.id.macroTypeView);

        progressBtn = (Button) findViewById(R.id.show_progress);

        deleteBtn = (Button) findViewById(R.id.deleteBtn);

        changeMacroBtn = (Button) findViewById(R.id.macroChange);

        if(user != null){
            macroTypeFromUser = user.getMacroType();
            currentName.setText(currentUserName);
            if(macroTypeFromUser == 1)
                macroType.setText("Intensywny");
            else
                macroType.setText("Objętościowy");
        }else{
            currentName.setText(currentUserName);
            macroType.setText("");
        }

        progressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null){
                    Toast.makeText(getApplicationContext(),"Nie posiadasz konta !", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(ShowProfiles.this, ViewProgress.class);
                    startActivity(intent);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(user != null){
                        userDao.delete(user);
                        SharedPreferences.Editor editor = userPrefs.edit();
                        SharedPreferences preferences2 = getApplicationContext().getSharedPreferences("Progress", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.clear();
                        editor2.apply();
                        editor.remove("logIn");
                        editor.remove("userName");
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Usunięto",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Nie posiadasz konta",Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        changeMacroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser;
                if(user == null){
                    Toast.makeText(getApplicationContext(),"Nie posiadasz konta !",Toast.LENGTH_LONG).show();
                }else{
                    if(macroTypeFromUser == 1) {
                        newUser = new User(currentUserName, 2);
                    }else{
                        newUser = new User(currentUserName, 1);
                    }
                    try {
                        userDao.delete(user);
                        userDao.create(newUser);
                        SharedPreferences preferences2 = getApplicationContext().getSharedPreferences("Progress", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.clear();
                        editor2.apply();
                        Toast.makeText(getApplicationContext(),"Zmieniono",Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private User getByUserName(String userName){
        try {
            QueryBuilder<User, Integer> qb = userDao.queryBuilder();
            qb.where().eq("user_name", userName);
            PreparedQuery<User> pq = qb.prepare();
            return userDao.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
