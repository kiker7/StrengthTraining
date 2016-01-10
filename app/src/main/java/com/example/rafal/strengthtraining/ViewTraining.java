package com.example.rafal.strengthtraining;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.Macrocycle;
import com.example.rafal.strengthtraining.data.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private String[] array = getWeekList();
    private int [] positionArray;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        positionArray = setDoneWeekArray();
        header = (TextView) findViewById(R.id.selection);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/InfantylFat.ttf");
        header.setTypeface(customFont);
        header.setTextSize(60);

        setListAdapter(new IconicAdapter());

        String userName = getUserNameFromPref();
        int macroType;

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
            qb.where().eq("user_name", userName);
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

    private int[] setDoneWeekArray(){
        SharedPreferences userPrefs = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
        String name = userPrefs.getString("userName",null);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
        Map<String, ?> allPreffs = preferences.getAll();
        Set<String> set = allPreffs.keySet();
        int [] arr = new int[]{9,9,9,9,9,9,9,9};
        int i =0;
        for(String e: set){
            if(allPreffs.get(e).equals(true)) {
                arr[i] = Integer.valueOf(e);
            }
            i++;
        }
        return arr;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        List<String> exercisesList = convertStringToArrayList(macro);
        List<String> subExeList = new ArrayList<String>();
        Intent intent = new Intent(ViewTraining.this, ShowWeek.class);
        if(!exercisesList.isEmpty()){
            if(exercisesList.size() == 504){
                for(int i = 0; i < 8; i++){
                    if(i == position){
                        subExeList =  exercisesList.subList(position * 63, position * 63 + 63);
                        break;
                    }
                }
            }else if(exercisesList.size() == 588){
                for(int i = 0; i < 8; i++){
                    if(i == position && i < 4){
                        subExeList = exercisesList.subList(position * 63, position * 63 + 63);
                        break;
                    }else if(i == position && i == 4){
                        subExeList =  exercisesList.subList(position * 63, position * 63 + 84);
                        break;
                    }else if(i == position && i > 4){
                        subExeList = exercisesList.subList((position - 1) * 84, position * 84);
                        break;
                    }
                }
            }
        }
        ArrayList<String> passList = new ArrayList<>(subExeList);
        passList.add(String.valueOf(position));
        intent.putStringArrayListExtra("list", passList);
        startActivity(intent);
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

    class IconicAdapter extends ArrayAdapter {
        IconicAdapter() {
            super(ViewTraining.this, R.layout.row, array);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row=convertView;

            if (row==null) {
                LayoutInflater inflater=getLayoutInflater();

                row=inflater.inflate(R.layout.row, parent, false);
            }

            TextView label=(TextView)row.findViewById(R.id.label);
            Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/InfantylFat.ttf");
            label.setTypeface(customFont);
            label.setText(array[position]);

            ImageView icon=(ImageView)row.findViewById(R.id.icon);

            for(int i =0 ; i < positionArray.length; i++){
                if (positionArray[i] == position) {
                    icon.setImageResource(R.drawable.ok_32);
                    break;
                }
            }

            return(row);
        }
    }
}
