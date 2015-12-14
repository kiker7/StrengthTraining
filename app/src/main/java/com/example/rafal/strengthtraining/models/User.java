package com.example.rafal.strengthtraining.models;

import com.example.rafal.strengthtraining.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rafal on 13.12.15.
 */

@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(id = true)
    int _id;
    @DatabaseField
    String name;
    @DatabaseField
    int macroType;

    public User(){}

    public User( String name, int macroType) {
        this.name = name;
        this.macroType = macroType;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMacroType() {
        return macroType;
    }

    public void setMacroType(int macroType) {
        this.macroType = macroType;
    }

    public int save(Repo repo){
        if(repo.Users.getUserByName(name) == null){
            return repo.Users.create(this);
        }else{
            return repo.Users.update(this);
        }
    }

    public int delete(Repo repo){
        return repo.Users.delete(this);
    }

    public String toString(){
        return name;
    }

}
