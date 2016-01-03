package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<String> nameList;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_details);
        list = getIntent().getExtras().getStringArrayList("list");
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

//    private List<String> setPartNameInList(List<String> list){
//        if(list.size() == 21){
//            for(int i = 0 ; i < 28; i++){
//                switch (i){
//                    case 0:
//                        list.add(i,"Biceps");
//                        continue;
//                    case 4:
//                        list.add(i,"Triceps");
//                        continue;
//                    case 8:
//                        list.add(i,"Barki");
//                        continue;
//                    case 12:
//                        list.add(i,"Klatka");
//                        continue;
//                    case 16:
//                        list.add(i,"Plecy");
//                        continue;
//                    case 20:
//                        list.add(i,"Nogi");
//                        continue;
//                    case 24:
//                        list.add(i,"Brzuch");
//                }
//            }
//        }else if(list.size() == 28){
//            for(int i = 0 ; i < 35; i++){
//                switch (i){
//                    case 0:
//                        list.add(i,"Biceps");
//                        continue;
//                    case 5:
//                        list.add(i,"Triceps");
//                        continue;
//                    case 10:
//                        list.add(i,"Barki");
//                        continue;
//                    case 15:
//                        list.add(i,"Klatka");
//                        continue;
//                    case 20:
//                        list.add(i,"Plecy");
//                        continue;
//                    case 25:
//                        list.add(i,"Nogi");
//                        continue;
//                    case 30:
//                        list.add(i,"Brzuch");
//                }
//            }
//        }
//        return list;
//    }

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
