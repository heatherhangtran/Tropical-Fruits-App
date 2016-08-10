package com.example.randybiglow.tropical_fruits_app;

/**
 * Created by HangTran on 5/5/16.
 */
public class Fruits {
    private int _id, _image;
    private String _name, _region, _season, _medicinal, _description;

    public Fruits() {
        //Following SQLite tutorial. Requires empty constructor.
        //This prevents someone from accidentally instantiating the contract class (this class), I have to give it an empty constructor.
    }

    //Constructors to utilize variables.
    public Fruits (int id, String name, String region, String season, String medicinal, String description, int image) {
        this._id = id;
        this._name = name;
        this._region = region;
        this._season = season;
        this._medicinal = medicinal;
        this._description = description;
        this._image = image;
    }

    //Setting a separate Constructor to hold only Strings.
    public Fruits (String name, String region, String season, String medicinal, String description, int image) {
        this._name = name;
        this._region = region;
        this._season = season;
        this._medicinal = medicinal;
        this._description = description;
        this._image = image;
    }

    //Separate getters and setters for each String and int:
    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getRegion() {
        return this._region;
    }

    public void setRegion(String region) {
        this._region = region;
    }

    public String getSeason() {
        return this._season;
    }

    public void setSeason(String season) {
        this._season = season;
    }

    public String getMedicinal() {
        return this._medicinal;
    }

    public void setMedicinal(String medicinal) {
        this._medicinal = medicinal;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public int getImage() {
        return _image;
    }

    public void setImage(int _image) {
        this._image = _image;
    }
}
