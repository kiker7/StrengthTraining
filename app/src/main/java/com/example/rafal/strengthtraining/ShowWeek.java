package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_details);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewWeek);
        // ustawic dane
        TrainingListAdapter trainingListAdapter = new TrainingListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(trainingListAdapter);
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
