package com.example.rafal.strengthtraining.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.example.rafal.strengthtraining.R;
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

    // zwraca ilustracje dla cwiczenia
    public int getImageResourceIdForExercise(Context context){

        TypedArray imagesArray = context.getResources().obtainTypedArray(R.array.images_array);
        int id = 0;

        if(musclePart == 1) {
            return imagesArray.getResourceId((exeNumber -1),0);
        }else {
            for (int i = 1; i < imagesArray.length(); i++) {
                if(i == (((musclePart - 1) * 6) + exeNumber) - 1){
                    id = imagesArray.getResourceId(i,0);
                    break;
                }
            }
        }
        imagesArray.recycle();
        return id;
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
