package com.example.rafal.strengthtraining.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Kiker on 18.12.15.
 * Strength Training
 */
public class Macrocycle implements Serializable {

    @DatabaseField(generatedId = true, columnName = "macro_id")
    public int macroId;

    @DatabaseField(columnName = "macrocycle")
    public String macrocycle;

    @DatabaseField(columnName = "macro_type")
    public int macroType;

    public Macrocycle() {}

    public Macrocycle(String macrocycle, int macroType) {
        this.macrocycle = macrocycle;
        this.macroType = macroType;
    }

    @Override
    public String toString() {
        return "Macrocycle{" +
                "macrocycle='" + macrocycle + '\'' +
                ", macroType=" + macroType +
                '}';
    }
}
