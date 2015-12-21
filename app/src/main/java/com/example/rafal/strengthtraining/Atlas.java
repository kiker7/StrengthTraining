package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.os.Bundle;
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
                // DODAC INTENT I PRZESLAC OBIEKT DANEGO CWICZENIA
                // I WYSWIETLIC SZCZEGOLY CWICZENIA
                return false;
            }
        });
    }

    /* funkcja do pobrania danych z bazy i przygotowania kontenerow na naglowki i subitemy */

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

        listDataChild.put(listDataHeader.get(0), nameList.subList(0, 5));
        listDataChild.put(listDataHeader.get(1), nameList.subList(5, 11));
        listDataChild.put(listDataHeader.get(2), nameList.subList(11, 17));
        listDataChild.put(listDataHeader.get(3), nameList.subList(17, 23));
        listDataChild.put(listDataHeader.get(4), nameList.subList(23, 29));
        listDataChild.put(listDataHeader.get(5), nameList.subList(29, 35));
        listDataChild.put(listDataHeader.get(6), nameList.subList(35, 41));
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