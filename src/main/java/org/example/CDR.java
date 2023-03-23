package org.example;

public class CDR {
    private String phoneNumber;
    private String typeOfCall;
    //YYYYMMDDHH24MMSS
    private String startOfCall;
    private String endOfCall;
    private String tariffType;

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

    @Override
    public String toString() {
        return "CDR{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", typeOfCall='" + typeOfCall + '\'' +
                ", startOfCall='" + startOfCall + '\'' +
                ", endOfCall='" + endOfCall + '\'' +
                ", tariffType='" + tariffType + '\'' +
                '}';
    }
}
