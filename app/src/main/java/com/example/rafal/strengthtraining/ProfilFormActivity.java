package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.rafal.strengthtraining.data.DatabaseHelper;
import com.example.rafal.strengthtraining.data.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ProfilFormActivity extends Activity {

    Button sendButton;
    EditText userName;
    RadioGroup radioGroup;
    RadioButton intensive, volume;
    DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_form);

        sendButton = (Button) findViewById(R.id.send_button);
        userName = (EditText) findViewById(R.id.profil_form_name_field);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupProfil);
        intensive = (RadioButton) findViewById(R.id.intensive);
        volume = (RadioButton) findViewById(R.id.volume);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String msg;
                String name = userName.getText().toString();
                int macroType = 1; // domyslnie intensywny

                if (selectedId == -1){
                    msg = "Wybierz rodzaj treningu";
                    Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                }else if(selectedId == intensive.getId() ){
                    macroType = 1;
                }else if (selectedId == volume.getId()){
                    macroType = 2;
                }
                if(TextUtils.isEmpty(name)) {
                    userName.setError("Wpisz swoje imię !");
                }else {
                    if (selectedId != -1) {
                        User user = new User(name, macroType);
                        try {
                            final Dao<User,Integer> userDao = getHelper().getUserDao();
                            userDao.create(user);
                            SharedPreferences preferences = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("logIn", true);
                            editor.putString("userName",name);
                            editor.apply();
                            Intent intent = new Intent(ProfilFormActivity.this, ViewTraining.class);
                            startActivity(intent);
                            finish();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
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
