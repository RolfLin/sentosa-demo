package edu.illinois.adsc.sentosa.query.Interface;

/**
 * Created by robert on 28/12/16.
 */
public class Attraction {
    public int id;
    public double x,y;
    public String name;
//    public String openingTime;
    public int startTimeHour;
    public int startTimeMin;
    public int endTimeHour;
    public int endTimeMin;
    public String introduce;
    public Attraction(int id, double x, double y, String name, int startTimeHour, int startTimeMin, int endTimeHour, int endTimeMin) {
        this(id, x, y, name, startTimeHour, startTimeMin, endTimeHour, endTimeMin, "N/A");
    }
    public Attraction(int id, double x, double y, String name, int startTimeHour, int startTimeMin, int endTimeHour, int endTimeMin, String description) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
        this.startTimeHour = startTimeHour;
        this.startTimeMin = startTimeMin;
        this.endTimeHour = endTimeHour;
        this.endTimeMin = endTimeMin;
        this.introduce = description;
    }
}