package com.example.petscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddPetActivity extends AppCompatActivity {

    private EditText nameText;

    private Pet selectedPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet); // zdefiniowanie interfejsu z pliku XML
        initWidgets();

        ImageButton imageButton = findViewById(R.id.image_view_return); // gdy uzytkownik kliknie na logo to wraca na strone glowna
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void initWidgets() {
        nameText = findViewById(R.id.nameText); // odnalezienie nameText z interfejsu uzytkownika poprzez metode findViewById() i zapisania go do zmiennej nameText aby odwolywac sie do niego pozniej w kodzie
    }

    public void savePetName(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this); //tworzenie instancji bazy danych SQLite
        String name = String.valueOf(nameText.getText());

        int id = Pet.petArrayList.size();
        Pet newPet = new Pet(id, name);
        Pet.petArrayList.add(newPet); //dodanie obiektu do listy
        sqLiteManager.addPetToDatabase(newPet); //dodanie obektu do bazy
        finish();

    }


}