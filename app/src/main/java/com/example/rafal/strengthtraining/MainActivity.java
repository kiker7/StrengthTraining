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

    Button atlasBtn, profileBtn, clear,progress;
    DatabaseHelper databaseHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlasBtn = (Button) findViewById(R.id.button2);
        profileBtn = (Button) findViewById(R.id.button);
        clear = (Button) findViewById(R.id.button4);
        progress = (Button) findViewById(R.id.button3);

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowProfiles.class);
                startActivity(intent);
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
                SharedPreferences preferences2 = getApplicationContext().getSharedPreferences("Progress", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                SharedPreferences.Editor editor2 = preferences2.edit();
                editor2.clear();
                editor2.apply();
                editor.remove("logIn");
                editor.remove("userName");
                editor.apply();
                Toast.makeText(getApplicationContext(),"Wyczyszczono", Toast.LENGTH_SHORT).show();
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
