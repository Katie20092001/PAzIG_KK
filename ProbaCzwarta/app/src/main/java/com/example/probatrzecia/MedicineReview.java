package com.example.probatrzecia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Date;

public class MedicineReview extends AppCompatActivity {

    private Pet selectedPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_review); // zdefiniowanie interfejsu z pliku XML
        checkForEditPet();
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


    private void checkForEditPet()
    {
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

    private void refreshListView() {

    }
}