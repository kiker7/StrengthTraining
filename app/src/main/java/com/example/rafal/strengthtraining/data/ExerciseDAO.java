package com.example.rafal.strengthtraining.data;

import com.example.rafal.strengthtraining.models.Exercise;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rafal on 13.12.15.
 */
public class ExerciseDAO {

    Dao<Exercise,Integer> exerciseDAO;

    public ExerciseDAO(DatabaseHelper db){

        try{
            exerciseDAO = db.getExerciseDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Exercise exercise){

        try{
            return exerciseDAO.create(exercise);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int update(Exercise exercise){

        try{
            return exerciseDAO.update(exercise);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int delete(Exercise exercise){

        try{
            return exerciseDAO.delete(exercise);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public Exercise getExerciseByIdentifier(int musclePart, int exInPart){

        try{
            QueryBuilder<Exercise,Integer> qb = exerciseDAO.queryBuilder();
            Where<Exercise,Integer> where = qb.where().eq("musclePart",musclePart );
//            where.and();
//            where.eq("exInPart",exInPart);

            PreparedQuery<Exercise> pq = qb.prepare();
            return exerciseDAO.queryForFirst(pq);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Exercise> getAll(){

        try{
            return exerciseDAO.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
