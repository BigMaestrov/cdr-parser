package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Abonent {
    private String phoneNumber;
    private String tariffType;
    private List<CDR> calls;
    private double totalCost;
    private Date totalDuration;

    public Date getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Date totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void addTotalDuration(Long addedDuration) {
        this.totalDuration = new Date(totalDuration.getTime() + addedDuration);
    }

    @Override
    public String toString() {
        String stringCalls = "";
        for (CDR buff: calls) {
            stringCalls +=buff.toString();
        }
        for (CDR buff: calls) {
            totalCost +=buff.getCost();
        }
        return "\nTariff index: "+ tariffType+"\n" +
                "----------------------------------------------------------------------------\n" +
                "Report for phone number "+phoneNumber+"\n" +
                "----------------------------------------------------------------------------\n" +
                "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                "----------------------------------------------------------------------------\n" +
                stringCalls+
                "----------------------------------------------------------------------------\n" +
                "|                                                      Total Cost: |  "+totalCost+"   |\n" +
                "----------------------------------------------------------------------------";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public List<CDR> getCalls() {
        return calls;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    //Подсчет итоговой стоимости
    public static String getTotalCost() {
        return "";
    }

    //Подсчет промежуточной стоимости
    public static String getCVRCost() {
        return "";
    }

    //Проверка является ли строка числом
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Abonent(CDR cdr) {
        totalDuration = new Date(0+cdr.getDuration().getTime());
        phoneNumber = cdr.getPhoneNumber();
        tariffType = cdr.getTariffType();
        calls = new ArrayList<CDR>();
        calls.add(cdr);
    }

    public Abonent() {
        totalDuration = new Date(0);
        calls = new ArrayList<CDR>();
    }
}
