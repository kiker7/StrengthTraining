package com.example.rafal.strengthtraining.data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Kiker on 18.12.15.
 * Strength Training
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws SQLException, IOException{
        writeConfigFile("ormlite_config.txt");
    }
}
