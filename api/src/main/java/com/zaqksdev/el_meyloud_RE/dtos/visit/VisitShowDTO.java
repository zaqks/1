package com.zaqksdev.el_meyloud_RE.dtos.visit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zaqksdev.el_meyloud_RE.models.Visit;

public class VisitShowDTO extends Visit {
    private int id;

    private int hour;
    private int minute;

    private int day;
    private int month;
    private int year;

    String date, time;

    public VisitShowDTO() {
    }

    public VisitShowDTO(Visit visit) {
        this.setId(visit.getId());
        this.setAgent(visit.getAgent());
        this.setClient(visit.getClient());
        this.setOffer(visit.getOffer());
        this.setPassed(visit.isPassed());
        this.setMissed(visit.isMissed());
        this.setAgent(visit.getAgent());

        Calendar clndr = visit.getDatetime();

        hour = clndr.get(Calendar.HOUR_OF_DAY);
        minute = clndr.get(Calendar.MINUTE);

        day = clndr.get(Calendar.DAY_OF_MONTH);
        month = clndr.get(Calendar.MONTH) + 1; // months start from 0 !!!!
        year = clndr.get(Calendar.YEAR);

        time = String.format("%dh" + (minute < 10 ? "0" : "") + "%d", hour, minute);
        date = String.format("%d/%d/%d", month, day, year);

    }

    // not init
    public List<VisitShowDTO> VisitShowDTOs(List<Visit> visits) {
        List<VisitShowDTO> rslt = new ArrayList<VisitShowDTO>();

        for (int i = 0; i < visits.size(); i++)
            rslt.add(new VisitShowDTO((visits.get(i))));

        return rslt;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
