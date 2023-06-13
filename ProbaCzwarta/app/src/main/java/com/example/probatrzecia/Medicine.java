package com.example.probatrzecia;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Medicine {

    public static ArrayList<Medicine> medicineArrayList = new ArrayList<>();
    private int id_medicine;
    private int id_pet;
    private String medicineName;
    private String medicineDescription;
    private Date date;
    private Time time;

    public Medicine(int id_medicine, int id, String medicineName, String medicineDescription, Date date, Time time) {
        this.id_medicine = id_medicine;
        this.id_pet = id;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.date = date;
        this.time = time;
    }

    public Medicine(int id_medicine, String medicineName, String medicineDescription, Date date, Time time) {
        this.id_medicine = id_medicine;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.date = date;
        this.time = time;
    }

    public static Medicine findMedicineById(int id_medicine) { // wyszukiwanie zwierzecia w liscie o podanym id
        for (Medicine medicine : medicineArrayList) {
            if (medicine.getId_medicine() == id_medicine) {
                return medicine;
            }
        }
        return null;
    }

    public int getId_medicine() {
        return id_medicine;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}




