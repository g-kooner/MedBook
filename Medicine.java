package com.example.medbook;

import java.util.Date;

public class Medicine {
    /*
    field varaibles of Medicine
     */
    String name;
    String startDate;
    int dose;
    String doseUnit;
    int dailyFrequency;

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



}
