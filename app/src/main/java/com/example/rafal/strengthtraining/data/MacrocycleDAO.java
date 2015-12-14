package com.example.rafal.strengthtraining.data;

import com.example.rafal.strengthtraining.models.Macrocycle;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rafal on 13.12.15.
 */
public class MacrocycleDAO {

    Dao<Macrocycle,Integer> macrocycleDAO;

    public MacrocycleDAO(DatabaseHelper db){

        try{
            macrocycleDAO = db.getMacrocycleDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Macrocycle macrocycle){

        try{
            return macrocycleDAO.create(macrocycle);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int update(Macrocycle macrocycle){

        try{
            return macrocycleDAO.update(macrocycle);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int delete(Macrocycle macrocycle){

        try{
            return macrocycleDAO.delete(macrocycle);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public Macrocycle getMacrocycleByType(int type){

        try{
            QueryBuilder<Macrocycle,Integer> qb = macrocycleDAO.queryBuilder();
            qb.where().eq("macroType", type);

            PreparedQuery<Macrocycle> pq = qb.prepare();
            return macrocycleDAO.queryForFirst(pq);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Macrocycle> getAll(){

        try{
            return macrocycleDAO.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
