package com.example.probatrzecia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView petListView; // reprezentacja elementu ListView z interfejsu


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // zdefiniowanie interfejsu z pliku XML
        initWidgets();
        loadFromDBToMemory();
        setPetAdapter();
        setOnClickListener();
    }


    //Utworzenie instancji SQLiteManager i wywolanie populatePetListArray(), pobierajaca dane z bazy i zapisujaca je w liscie statycznej
    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populatePetListArray();
    }

    private void initWidgets()
    {
        petListView = findViewById(R.id.pet_list_view);
    } // odnalezienie ListView z interfejsu
    private void setPetAdapter() {
        PetAdapter petAdapter = new PetAdapter(getApplicationContext(), Pet.petArrayList);
        petListView.setAdapter(petAdapter); // dostarczanie danych dla elementow ListView
    }

    private void setOnClickListener() {
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Pet selectedPet = (Pet) petListView.getItemAtPosition(position);
                int idPet = selectedPet.getId();

                Intent medicineIntent = new Intent(MainActivity.this, MedicineReview.class);
                medicineIntent.putExtra(Pet.PET_EDIT_EXTRA, idPet);
                startActivity(medicineIntent);

//                Intent addMedicineIntent = new Intent(MainActivity.this, AddMedicine.class);
//                addMedicineIntent.putExtra(Pet.PET_EDIT_EXTRA, idPet);
//                startActivity(addMedicineIntent);
            }
        });
    }


    public void addPet(View view)
    {
        Intent newPetIntent = new Intent(this, AddPetActivity.class);
        startActivity(newPetIntent); // przekierowanie na interfejs umozliwiajacy wprowadzenie imienia zwierzecia
    }

//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Pet selectedPet = Pet.petArrayList.get(position);
//        int petId = selectedPet.getId();
//
//        Intent medicineReviewIntent = new Intent(this, MedicineReview.class);
//        medicineReviewIntent.putExtra("petId", petId);
//        startActivity(medicineReviewIntent);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Pet.petArrayList.isEmpty()) {
            loadFromDBToMemory(); // wczytanie danych z bazy danych do listy
        }
        setPetAdapter(); // wyswietlenie danych zwierzat
    }

}