package com.example.rafal.strengthtraining;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;




public class MainActivity extends AppCompatActivity {

    Button atlasBtn, profileBtn,progress;
    DatabaseHelper databaseHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlasBtn = (Button) findViewById(R.id.button2);
        profileBtn = (Button) findViewById(R.id.button);
        progress = (Button) findViewById(R.id.button3);

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowProfiles.class);
                startActivity(intent);
            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLogIn()){
                    Intent intent = new Intent(MainActivity.this, ViewTraining.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, ProfilFormActivity.class);
                    startActivity(intent);
                }
            }
        });

        atlasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Atlas.class);
                startActivity(intent);
            }
        });

    }

    // sprawdza czy uzytkownik jest zalogowany
    private boolean checkLogIn(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
        return preferences.getBoolean("logIn", false);
    }

    /**********************************************
     * potencjalnie do kopiowania przy połaczeniu z baza
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
