package org.example;

import java.util.ArrayList;
import java.util.List;

public class Abonent {
    private String phoneNumber;
    private String tariffType;
    private List<CDR> calls;
    private int totalCost;

    @Override
    public String toString() {
        String stringCalls = "";
        for (CDR buff: calls) {
            stringCalls +=buff.toString();
        }
        return "\nTariff index: "+ tariffType+"\n" +
                "----------------------------------------------------------------------------\n" +
                "Report for phone number "+phoneNumber+"\n" +
                "----------------------------------------------------------------------------\n" +
                "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                "----------------------------------------------------------------------------\n" +
                stringCalls+
                "----------------------------------------------------------------------------\n" +
                "|                                                      Total Cost: |   "+totalCost+"  |\n" +
                "----------------------------------------------------------------------------\n";
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
        phoneNumber = cdr.getPhoneNumber();
        tariffType = cdr.getTariffType();
        calls = new ArrayList<CDR>();
        calls.add(cdr);
    }

    public Abonent() {
        calls = new ArrayList<CDR>();
    }
}
