package com.example.randybiglow.tropical_fruits_app;

/**
 * Created by RandyBiglow on 5/2/16.
 */
public class Fruits {
    int id;
    String common_name, region, season, medicinal;

    public Fruits(int id, String common_name, String region, String season, String medicinal) {
        this.id = id;
        this.common_name = common_name;
        this.region = region;
        this.season = season;
        this.medicinal = medicinal;
    }

    @Override
    public String toString() {
        return "Fruits{" +
                "id=" + id +
                ", common_name" + common_name + '\'' +
                ", region=" + region + '\'' +
                ", season=" + season + '\'' +
                ", medicinal=" + medicinal + '\'' +
                '}';
    }
}
