package org.example;

public class CDR {
    private String phoneNumber;
    private String typeOfCall;
    //YYYYMMDDHH24MMSS
    private String startOfCall;
    private String endOfCall;
    private String tariffType;
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

    public String getStartOfCall() {
        return startOfCall;
    }

    public void setStartOfCall(String startOfCall) {
        this.startOfCall = startOfCall;
    }

    public String getEndOfCall() {
        return endOfCall;
    }

    public void setEndOfCall(String endOfCall) {
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

    @Override
    public String toString() {
        return "|     02    | 2023-10-11 14:00:17 | 2023-10-11 14:10:19 | 00:10:02 |  "+cost+" |\n";
    }
}
