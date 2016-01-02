package com.example.rafal.strengthtraining;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.Macrocycle;
import com.example.rafal.strengthtraining.data.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ViewTraining extends ListActivity {

    private DatabaseHelper databaseHelper = null;
    private List<Macrocycle> macrocycleList;
    private Dao<User, Integer> userDao;
    private User user;
    private String macro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        String userName = getUserNameFromPref();

        int macroType;
        String[] array = getWeekList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        setListAdapter(adapter);

        try{
            final Dao<Macrocycle,Integer> macrocycleDao = getHelper().getMacrocyclesDao();
            macrocycleList = macrocycleDao.queryForAll();
            userDao = getHelper().getUserDao();
            user = getByUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user != null){
            macroType = user.getMacroType();
            macro = getMacroByType(macroType);
        }

    }

    private String getMacroByType(int type){
        for (Macrocycle e: macrocycleList
             ) {
            if(e.getMacroType() == type){
                return e.getMacrocycle();
            }
        }
        return null;
    }

    private User getByUserName(String userName){
        try {
            QueryBuilder<User, Integer> qb = userDao.queryBuilder();
            qb.where().eq("userName", userName);
            PreparedQuery<User> pq = qb.prepare();
            return userDao.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUserNameFromPref(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Session",MODE_PRIVATE);
        return preferences.getString("userName", null);
    }

    private String [] getWeekList(){
        String [] array = new String[8];
        String week = " tydzień";
        for(int i = 0; i < 8; i++){
            array[i] = String.valueOf(i+1) + week;
        }
        return array;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // przeslac dokladna czesc makrocyklu na podstawie position


    }

    private ArrayList<String> convertStringToArrayList(String str){
        String strSeparator = ",";
        String[] arr = str.split(strSeparator);
        return  new ArrayList<>(Arrays.asList(arr));
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
