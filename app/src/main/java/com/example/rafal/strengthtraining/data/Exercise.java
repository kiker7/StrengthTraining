package com.example.rafal.strengthtraining.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Kiker on 18.12.15.
 * Strength Training
 */
public class Exercise implements Serializable {

    @DatabaseField(generatedId = true, columnName = "exercise_id")
    public int exerciseId;

    @DatabaseField(columnName = "name")
    public String name;

    @DatabaseField(columnName = "description")
    public String description;

    @DatabaseField(columnName = "muscle_part")
    public int musclePart;

    @DatabaseField(columnName = "exe_number")
    public int exeNumber;

    public Exercise(){ }

    public Exercise(String name, String description, int musclePart, int exeNumber) {
        this.name = name;
        this.description = description;
        this.musclePart = musclePart;
        this.exeNumber = exeNumber;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMusclePart() {
        return musclePart;
    }

    public void setMusclePart(int musclePart) {
        this.musclePart = musclePart;
    }

    public int getExeNumber() {
        return exeNumber;
    }

    public void setExeNumber(int exeNumber) {
        this.exeNumber = exeNumber;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", musclePart=" + musclePart +
                ", exeNumber=" + exeNumber +
                '}';
    }
}
