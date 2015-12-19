package com.example.rafal.strengthtraining.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafal.strengthtraining.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Kiker on 18.12.15.
 * Strength Training
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "trainDB.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<User,Integer> userDao;
    private Dao<Exercise,Integer> exercisesDao;
    private Dao<Macrocycle,Integer> macrocyclesDao;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, Macrocycle.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Nie da sie utworzyc bazy", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Exercise.class, true);
            TableUtils.dropTable(connectionSource, Macrocycle.class, true);
        }catch (SQLException e){
            Log.e(DatabaseHelper.class.getName(), "Nie da sie zaktualizowac bazy z wersji " +oldVersion+ " do " +newVersion+ "wersji", e );
        }
    }

    public Dao<User, Integer> getUserDao() throws SQLException{
        if(userDao == null){
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Exercise, Integer> getExerciseDao() throws SQLException{
        if(exercisesDao == null){
            exercisesDao = getDao(Exercise.class);
        }
        return exercisesDao;
    }

    public Dao<Macrocycle, Integer> getMacrocyclesDao() throws SQLException{
        if(macrocyclesDao == null){
            macrocyclesDao = getDao(Macrocycle.class);
        }
        return macrocyclesDao;
    }

}
