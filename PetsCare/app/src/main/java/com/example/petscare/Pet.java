package com.example.petscare;

import java.util.ArrayList;

public class Pet {

    public static ArrayList<Pet> petArrayList = new ArrayList<>();
    public static String PET_EDIT_EXTRA = "petEdit";
    private int id;
    private String name;



    public Pet(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public static Pet findPetById(int id) { // wyszukiwanie zwierzecia w liscie o podanym id
        for (Pet pet : petArrayList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
