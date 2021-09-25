package com.example.kooner_medbook;

public class Medicine {
    /*
    field varaibles of Medicine
    */
    private String name;
    private String startDate;
    private int dose;
    private String doseUnit;
    private int dailyFrequency;

    Medicine(String name,String startDate, int dose, String doseUnit, int dailyFrequency){
        this.name = name;
        this.startDate = startDate;
        this.dose = dose;
        this.doseUnit= doseUnit;
        this.dailyFrequency = dailyFrequency;
    }

    public String strRepr(){
        String repr = String.format("%s\n dose: %d, unit: %s, dailyFrequency: %d", name, dose, doseUnit, dailyFrequency);
        return repr;
    }

    /*
    getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

    public int getDailyFrequency() {
        return dailyFrequency;
    }

    public void setDailyFrequency(int dailyFrequency) {
        this.dailyFrequency = dailyFrequency;
    }
}
