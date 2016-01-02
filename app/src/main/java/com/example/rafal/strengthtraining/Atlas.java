package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

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
public class Atlas extends Activity {

    private List<String> listDataHeader;
    private HashMap<String,List<String>> listDataChild;
    private DatabaseHelper databaseHelper = null;
    private List<Exercise> exerciseList;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atlas_main);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        getData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Exercise e = getExercise(groupPosition,childPosition);
                Intent intent = new Intent(Atlas.this, ShowExerciseDetail.class);
                intent.putExtra("Exercise", e);
                startActivity(intent);
                return false;
            }
        });
    }

    public Exercise getExercise(int groupPosition, int childPosition){

        Exercise exercise = new Exercise();
        if(!exerciseList.isEmpty()){
        for (Exercise e: exerciseList
             ) {
            if((e.getMusclePart() - 1) == groupPosition  && (e.getExeNumber() - 1) == childPosition){
                exercise = e;
                break;
            }else
                exercise = null;
        }
        return exercise;
        }else {
            Log.d("LISTA","PUSTA LISTA");
            return null;
        }
    }



    private void getData(){
        try{
            final Dao<Exercise,Integer> exercisesDao = getHelper().getExerciseDao();
            exerciseList = exercisesDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        ArrayList<String> nameList = new ArrayList<>();

        listDataHeader.add("Biceps");
        listDataHeader.add("Triceps");
        listDataHeader.add("Barki");
        listDataHeader.add("Klatka");
        listDataHeader.add("Plecy");
        listDataHeader.add("Nogi");
        listDataHeader.add("Brzuch");

        for (Exercise e: exerciseList
             ) {
            nameList.add(e.getName());
        }

        listDataChild.put(listDataHeader.get(0), nameList.subList(0, 6));
        listDataChild.put(listDataHeader.get(1), nameList.subList(6, 12));
        listDataChild.put(listDataHeader.get(2), nameList.subList(12, 18));
        listDataChild.put(listDataHeader.get(3), nameList.subList(18, 24));
        listDataChild.put(listDataHeader.get(4), nameList.subList(24, 30));
        listDataChild.put(listDataHeader.get(5), nameList.subList(30, 36));
        listDataChild.put(listDataHeader.get(6), nameList.subList(36, exerciseList.size()));
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
