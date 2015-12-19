package com.example.rafal.strengthtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;




public class MainActivity extends AppCompatActivity {

    Button atlasBtn;
    TextView textViewDB;
    DatabaseHelper databaseHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlasBtn = (Button) findViewById(R.id.button2);
        textViewDB = (TextView) findViewById(R.id.textViewDB);

    }

    /**********************************************
     * potencjalnie do kopiowania
     **********************************************/

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

    /***********************************************
     * koniec
     ***********************************************/

}
