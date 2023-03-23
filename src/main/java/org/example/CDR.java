package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDR {
    private String phoneNumber;
    private String typeOfCall;
    //YYYYMMDDHH24MMSS
    private Date startOfCall;
    private Date endOfCall;
    private String tariffType;
    private Date duration;
    private double cost;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTypeOfCall() {
        return typeOfCall;
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall = typeOfCall;
    }

    public Date getStartOfCall() {
        return startOfCall;
    }

    public void setStartOfCall(Date startOfCall) {
        this.startOfCall = startOfCall;
    }

    public Date getEndOfCall() {
        return endOfCall;
    }

    public void setEndOfCall(Date endOfCall) {
        this.endOfCall = endOfCall;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    private double getCost(){
        return cost;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        DateFormat dateFormater1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = dateFormater1.format(startOfCall);
        String end = dateFormater1.format(endOfCall);
        DateFormat dateFormater2 = new SimpleDateFormat("HH:mm:ss");
        String dur = dateFormater2.format(duration);

        return "|     "+tariffType+"   | "+start+" | "+end+" | "+ dur+" |  "+cost+" |\n";
    }
}
