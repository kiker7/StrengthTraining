package com.example.rafal.strengthtraining.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafal.strengthtraining.models.Exercise;
import com.example.rafal.strengthtraining.models.Macrocycle;
import com.example.rafal.strengthtraining.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;



/**
 * Created by rafal on 13.12.15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "strengthtrainingDB.sqlite";
    private static final int DATABASE_VERSION = 3;

    private Dao<User,Integer> userDao = null;
    private Dao<Exercise,Integer> exerciseDao = null;
    private Dao<Macrocycle,Integer> macrocycleDao = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Inicjalizaca bazy
        DatabaseInitializer initializer = new DatabaseInitializer(context);
        try{
            initializer.createDatabase();
            initializer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            Log.i(DatabaseHelper.class.getName(), "onCreate");

            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, Macrocycle.class);

        }catch(SQLException e){
            Log.e(DatabaseHelper.class.getName(), "Nie da sie utworzyc bazy", e);
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Exercise.class, true);
            TableUtils.dropTable(connectionSource, Macrocycle.class, true);

            onCreate(database);

        }catch (SQLException e ){
            Log.e(DatabaseHelper.class.getName(),"Nie da sie usunac bazy",e);
            throw new RuntimeException();
        }
    }

    public Dao<User,Integer> getUserDao() throws SQLException{
        if (userDao == null){
            userDao = DaoManager.createDao(connectionSource, User.class);
        }
        return userDao;
    }

    public Dao<Exercise,Integer> getExerciseDao() throws SQLException{
        if(exerciseDao == null){
            exerciseDao = DaoManager.createDao(connectionSource, Exercise.class);
        }
        return exerciseDao;
    }

    public Dao<Macrocycle,Integer> getMacrocycleDao() throws SQLException{
        if (macrocycleDao == null){
            macrocycleDao = DaoManager.createDao(connectionSource, Macrocycle.class);
        }
        return macrocycleDao;
    }


    @Override
    public void close(){
        super.close();
        userDao = null;
        exerciseDao = null;
        macrocycleDao = null;
    }

}
