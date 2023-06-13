package com.example.probatrzecia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AddMedicine extends AppCompatActivity {

    private Medicine selectedMedicine;
    private EditText medicineName;
    private EditText description;
    private DatePicker date_picker;
    private TimePicker time_picker;

    private int id_medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

//        int idPet = getIntent().getIntExtra(Pet.PET_EDIT_EXTRA, -1);

        ImageButton imageButton = findViewById(R.id.image_view_return_vol4);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMedicine.this, MainActivity.class);
                startActivity(intent);
            }
        });
        initWidgets();
    }

    private void initWidgets() {
        medicineName = findViewById(R.id.medicineName);
        description = findViewById(R.id.description);
        date_picker = findViewById(R.id.date_picker);
        time_picker = findViewById(R.id.time_picker);
    }

    public void saveMedicine(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String medicine_name = String.valueOf(medicineName.getText());
        String medicine_description = String.valueOf(description.getText());

        int year = date_picker.getYear();
        int month = date_picker.getMonth();
        int day = date_picker.getDayOfMonth();
        Date dateMedicine = new Date(year - 1900, month, day);

        int hour = time_picker.getCurrentHour();
        int minute = time_picker.getCurrentMinute();
        Time timeMedicine = new Time(hour, minute, 0);

        int id = Medicine.medicineArrayList.size();
        Medicine newMedicine = new Medicine(id, 1, medicine_name, medicine_description, dateMedicine, timeMedicine);
        Medicine.medicineArrayList.add(newMedicine);
        sqLiteManager.addMedicineToDatabase(newMedicine);
        finish();
    }

//    public void saveMedicine(View view) {
//        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this); // Tworzenie instancji bazy danych SQLite
//
//        String medicine_name = String.valueOf(medicineName.getText());
//        String medicine_description = String.valueOf(description.getText());
//        String dateMedicine = String.valueOf(date_picker.getDate());
//        String timeMedicine = String.valueOf(time_picker.getTime());
//
//        int idMedicine = Medicine.medicineArrayList.size();
////
////        // Pobranie wybranego zwierzaka z ListView
////        int selectedPosition = pet_list_view.getSelectedItemPosition();
////        if (selectedPosition != ListView.INVALID_POSITION) {
////            Pet selectedPet = (Pet) pet_list_view.getItemAtPosition(selectedPosition);
////            int idPet = selectedPet.getId();
//
//            Medicine newMedicine = new Medicine(idMedicine, medicineName, description, dateMedicine, timeMedicine);
//            Medicine.medicineArrayList.add(newMedicine); // Dodanie obiektu do listy
//            sqLiteManager.addMedicineToDatabase(newMedicine); // Dodanie obiektu do bazy
//            finish();
//        }
//    }



//    private Date getDateFromDatePicker(DatePicker datePicker) {
//        int year = datePicker.getYear();
//        int month = datePicker.getMonth();
//        int day = datePicker.getDayOfMonth();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);
//        return calendar.getTime();
//    }
//
//    private Time getTimeFromTimePicker(TimePicker timePicker) {
//        int hour = timePicker.getCurrentHour();
//        int minute = timePicker.getCurrentMinute();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//
//        Time time = new Time(calendar.getTimeInMillis());
//        return time;
//    }

    }
