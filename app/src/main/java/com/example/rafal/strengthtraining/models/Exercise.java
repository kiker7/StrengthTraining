package com.example.rafal.strengthtraining.models;

import com.example.rafal.strengthtraining.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rafal on 29.1 1.15.
 */

@DatabaseTable(tableName = "exercise")
public class Exercise {

    @DatabaseField
    String name;
    @DatabaseField
    String description;
    @DatabaseField(id = true)
    int _id;
    @DatabaseField
    int musclePart;
    @DatabaseField
    int exInPart;

    public Exercise() {}

    public Exercise(String name, String description, int _id, int musclePart, int exInPart) {
        this.name = name;
        this.description = description;
        this._id = _id;
        this.musclePart = musclePart;
        this.exInPart = exInPart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getMusclePart() {
        return musclePart;
    }

    public void setMusclePart(int musclePart) {
        this.musclePart = musclePart;
    }

    public int getExInPart() {
        return exInPart;
    }

    public void setExInPart(int exInPart) {
        this.exInPart = exInPart;
    }

    public int save(Repo repo){
        if(repo.Exercises.getExerciseByIdentifier(musclePart,exInPart) == null){
            return repo.Exercises.create(this);
        }else{
            return repo.Exercises.update(this);
        }
    }

    public int delete(Repo repo){
        return repo.Exercises.delete(this);
    }

    public String toString(){
        return name;
    }

}
