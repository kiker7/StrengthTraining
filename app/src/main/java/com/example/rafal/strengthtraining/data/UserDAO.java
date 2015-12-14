package com.example.rafal.strengthtraining.data;

import com.example.rafal.strengthtraining.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rafal on 13.12.15.
 */
public class UserDAO {
    Dao<User,Integer> userDao;

    public UserDAO(DatabaseHelper db){

        try{
            userDao = db.getUserDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int create(User user){

        try{
            return userDao.create(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int update(User user){

        try{
            return userDao.update(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int delete(User user){

        try{
            return userDao.delete(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public User getUserByName(String name){

        try{
            QueryBuilder<User,Integer> qb = userDao.queryBuilder();
            qb.where().eq("name", name);

            PreparedQuery<User> pq = qb.prepare();
            return userDao.queryForFirst(pq);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAll(){

        try{
            return userDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
