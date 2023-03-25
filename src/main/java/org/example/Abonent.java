package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Abonent {
    private String phoneNumber;
    private String tariffType;
    private List<CDR> calls;
    private double totalCost;
    Date totalDuration;

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
        calculateTotalCost();
        double cost = getTotalCost();
        for (CDR buff : calls) {
            stringCalls += buff.toString();
        }
        return "\nTariff index: " + tariffType + "\n" +
                "----------------------------------------------------------------------------\n" +
                "Report for phone number " + phoneNumber + "\n" +
                "----------------------------------------------------------------------------\n" +
                "| Call Type |   Start Time        |     End Time        | Duration | Cost  \n" +
                "----------------------------------------------------------------------------\n" +
                stringCalls +
                "----------------------------------------------------------------------------\n" +
                "|                                          Total Cost: | " + cost + " rubles  \n" +
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
    public double getTotalCost() {
        return totalCost;
    }

    public void calculateTotalCost(){
        for (int i = 0; i < calls.size(); i++) {

            switch (calls.get(i).getTariffType().trim()) {
                //Тариф поминутный
                case ("03"):
                    calls.get(i).setCost((calls.get(i).getDuration().getTime() + 14400000) / 60_000 * 1.5);
                    totalCost += calls.get(i).getCost();
                    break;


                //Тариф обычный
                case ("11"):
                    //Если входящий
                    if (calls.get(i).getTypeOfCall().trim().equals("01")) {
                        //Если в период от начала до завершения разговора НЕ превышен лимит в 100 минут
                        if (getTotalDuration().getTime() < 100 * 60 * 1000 && (getTotalDuration().getTime()+calls.get(i).getDuration().getTime()) < 100 * 60 * 1000) {
                            calls.get(i).setCost((calls.get(i).getDuration().getTime() + 14400000) / 60_000 * 0.5);
                            //Если на момент начала разговора ПРЕВЫШЕН лимит в 100 минут
                        } else if(getTotalDuration().getTime() > 100 * 60 * 1000) {
                            calls.get(i).setCost((calls.get(i).getDuration().getTime() + 14400000) / 60_000 * 1.5);
                            //Если лимит будет превышен в момент разговора
                        } else {
                            double start = 100 * 60 * 1000 - calls.get(i).getStartOfCall().getTime();
                            double end = calls.get(i).getEndOfCall().getTime() - 100 * 60 * 1000;
                            calls.get(i).setCost((start + 14400000) / 60_000 * 0.5 + ((end + 14400000) / 60_000 * 1.5));
                        }
                        //Если изходящий
                    } else {
                        calls.get(i).setCost(0.0);
                    }
                    totalCost += calls.get(i).getCost();
                    break;


                //Тариф безлимит
                case ("06"):
                    //Если в период от начала до завершения разговора НЕ превышен лимит в 300 минут
                    if (getTotalDuration().getTime() < 300 * 60 * 1000 && (getTotalDuration().getTime()+calls.get(i).getDuration().getTime()) < 100 * 60 * 1000) {
                        calls.get(i).setCost(0.0);
                        setTotalCost(100);
                        //Если на момент начала разговора ПРЕВЫШЕН лимит в 300 минут
                    } else if(getTotalDuration().getTime() > 100 * 60 * 1000) {
                        calls.get(i).setCost((calls.get(i).getDuration().getTime() + 14400000) / 60_000 * 1.0);
                        //Если лимит будет превышен в момент разговора
                    } else {
                        double start = 100 * 60 * 1000 - calls.get(i).getStartOfCall().getTime();
                        double end = calls.get(i).getEndOfCall().getTime() - 100 * 60 * 1000;
                        calls.get(i).setCost((end + 14400000) / 60_000 * 1);
                    }
                    totalCost += calls.get(i).getCost();
                    break;
            }
        }
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
        totalDuration = new Date(0 + cdr.getDuration().getTime());
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
