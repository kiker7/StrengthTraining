package com.example.rafal.strengthtraining.models;

import com.example.rafal.strengthtraining.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rafal on 13.12.15.
 */

@DatabaseTable(tableName = "macrocycle")
public class Macrocycle {

    @DatabaseField(id = true)
    int _id;
    @DatabaseField
    int macrocycle;
    @DatabaseField
    int macroType;

    public Macrocycle(){}

    public Macrocycle(int _id, int macrocycle, int macroType) {
        this._id = _id;
        this.macrocycle = macrocycle;
        this.macroType = macroType;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getMacrocycle() {
        return macrocycle;
    }

    public void setMacrocycle(int macrocycle) {
        this.macrocycle = macrocycle;
    }

    public int getMacroType() {
        return macroType;
    }

    public void setMacroType(int macroType) {
        this.macroType = macroType;
    }

    public int save(Repo repo){
        if(repo.Macrocycles.getMacrocycleByType(macroType) == null){
            return repo.Macrocycles.create(this);
        }else{
            return repo.Macrocycles.update(this);
        }
    }

    public int delete(Repo repo){
        return repo.Macrocycles.delete(this);
    }

    public String toString(){
        return String.valueOf(macroType);
    }

}
