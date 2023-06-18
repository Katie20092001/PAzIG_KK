package com.example.petscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class MedicineReview extends AppCompatActivity {

        private Pet selectedPet;
        private ListView medicineListView; // reprezentacja elementu ListView z interfejsu

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_medicine_review); // zdefiniowanie interfejsu z pliku XML
            checkForEditPet();
            initWidgets();
            loadFromDBToMemory();
            setMedicineAdapter();

//        Intent intent = getIntent();
//        int petId = intent.getIntExtra("petId", -1);
//        intent.putExtra("petId", petId);
//        setResult(RESULT_OK, intent);
//
//        int selectedPetId = getIntent().getIntExtra(Pet.PET_EDIT_EXTRA, -1);
//        selectedPet = Pet.findPetById(selectedPetId);
//        Log.d("MedicineReview", "Selected pet id: " + selectedPetId);


            ImageButton imageButton = findViewById(R.id.image_view_return_vol3);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicineReview.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }


        private void checkForEditPet() {
            Intent previousIntent = getIntent();
            int passedPetID = previousIntent.getIntExtra(Pet.PET_EDIT_EXTRA, -1);
            selectedPet = Pet.findPetById(passedPetID);

//        if (selectedPet != null)
//        {
//            nameText.setText(selectedPet.getName());
//        }
//        else
//        {
//            deleteButton.setVisibility(View.INVISIBLE);
//        }
        }

        public void addMedicine(View view) {
            Intent intent = new Intent(MedicineReview.this, AddMedicine.class);
            startActivity(intent); // przeniesienie do aktywnosci dodawania leku
        }

//    public void deletePet(Pet pet) {
//        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
//        sqLiteManager.deletePetFromDatabase(pet);
//        refreshListView();
//    }

        public void deletePet(View view) {
            SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
            sqLiteManager.updatePetInDatabase(selectedPet);
            finish();
        }


    //Utworzenie instancji SQLiteManager i wywolanie populateMedicineListArray(), pobierajaca dane z bazy i zapisujaca je w liscie statycznej
    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateMedicineListArray();
    }


    // Odnalezienie ListView z interfejsu
    private void initWidgets()
    {
        medicineListView = findViewById(R.id.list_view_medicines);
    }


    // Dostarczanie danych dla elementow ListView
    private void setMedicineAdapter() {
        MedicineAdapter medicineAdapter = new MedicineAdapter(getApplicationContext(), Medicine.medicineArrayList);
        medicineListView.setAdapter(medicineAdapter);
    }


//    // Obsługa kliknięcia elementu ListView
//    private void setOnClickListener() {
//        medicineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Pet selectedPet = (Pet) petListView.getItemAtPosition(position);
//                int idPet = selectedPet.getId();
//
//                Intent medicineIntent = new Intent(MainActivity.this, MedicineReview.class);
//                medicineIntent.putExtra(Pet.PET_EDIT_EXTRA, idPet);
//                startActivity(medicineIntent);
//
////                Intent addMedicineIntent = new Intent(MainActivity.this, AddMedicine.class);
////                addMedicineIntent.putExtra(Pet.PET_EDIT_EXTRA, idPet);
////                startActivity(addMedicineIntent);
//            }
//        });
//    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Medicine.medicineArrayList.isEmpty()) {
            loadFromDBToMemory(); // wczytanie danych z bazy danych do listy
        }
        setMedicineAdapter(); // wyswietlenie danych zwierzat
    }

        private void refreshListView() {

        }
    }











