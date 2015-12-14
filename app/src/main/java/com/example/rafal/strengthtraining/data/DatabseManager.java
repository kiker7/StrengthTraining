package com.example.rafal.strengthtraining.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * Created by rafal on 13.12.15.
 */
public class DatabseManager {

    private DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public DatabaseHelper getHelper(Context context) {
        if (helper == null)
            helper = (DatabaseHelper) OpenHelperManager.getHelper(context, DatabaseHelper.class);

        return helper;

    }

    public void releaseHelper(DatabaseHelper helper){
        if (helper != null){
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }
}
