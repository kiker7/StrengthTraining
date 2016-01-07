package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.Exercise;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ShowWeek extends Activity {

    private List<String> listDataHeader;
    private HashMap<String,List<String>> listDataChild;
    private DatabaseHelper databaseHelper = null;
    private List<Exercise> exerciseList;
    private ArrayList<String> list;
    // nr tygodnia
    private String week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_details);
        Button footerBtn = (Button) findViewById(R.id.footerbutton);

        list = getIntent().getExtras().getStringArrayList("list");
        week = list.remove(list.size() -1);
        try{
            final Dao<Exercise,Integer> exercisesDao = getHelper().getExerciseDao();
            exerciseList = exercisesDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getData();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewWeek);
        TrainingListAdapter trainingListAdapter = new TrainingListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(trainingListAdapter);

        footerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences user = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
                String name = user.getString("userName",null);
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(week, true);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Zapisano",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getData(){
        ArrayList<String> list = setNameList();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("1 TRENING");
        listDataHeader.add("2 TRENING");
        listDataHeader.add("3 TRENING");



        if(list.size() == 63){
            listDataChild.put(listDataHeader.get(0), list.subList(0, 21));
            listDataChild.put(listDataHeader.get(1), list.subList(21, 42));
            listDataChild.put(listDataHeader.get(2), list.subList(42, 63));
        } else if (list.size() == 84) {
            listDataChild.put(listDataHeader.get(0), list.subList(0, 28));
            listDataChild.put(listDataHeader.get(1), list.subList(28, 56));
            listDataChild.put(listDataHeader.get(2), list.subList(56, 84));
        }
    }

    private ArrayList<String> setNameList(){
        ArrayList<String> result = new ArrayList<>();
        for (String e: list
             ) {
            result.add(getExercise(Integer.valueOf(String.valueOf(e.charAt(2))),Integer.valueOf(String.valueOf(e.charAt(3)))).getName());
        }
        return result;
    }

    public Exercise getExercise(int groupPosition, int childPosition){

        Exercise exercise = new Exercise();
        if(!exerciseList.isEmpty()){
            for (Exercise e: exerciseList
                    ) {
                if(e.getMusclePart() == groupPosition  && e.getExeNumber() == childPosition){
                    exercise = e;
                    break;
                }else
                    exercise = null;
            }
            return exercise;
        }else {
            Log.d("LISTA", "PUSTA LISTA");
            return null;
        }
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
