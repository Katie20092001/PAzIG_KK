package com.example.petscare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "AnimalDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Pets";
    // private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";

    private static final String TABLE_NAME_MEDICINES = "Medicines";
    private static final String ID_MEDICINE_FIELD = "id_medicine";
    //private static final String ID_PET_FIELD = "id_pet";
    private static final String MEDICINE_NAME_FIELD = "medicine_name";
    private static final String MEDICINE_DESCRIPTION_FIELD = "medicine_description";
    private static final String TIME_FIELD = "time";
    private static final String DATE_FIELD = "date";


    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
//                .append(COUNTER)
//                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(NAME_FIELD)
                .append(" TEXT)");


        StringBuilder medicineSql;
        medicineSql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME_MEDICINES)
                .append("(")
                .append(ID_MEDICINE_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
//                .append(ID_PET_FIELD)
//                .append(" INT, ")
                .append(MEDICINE_NAME_FIELD)
                .append(" TEXT, ")
                .append(MEDICINE_DESCRIPTION_FIELD)
                .append(" TEXT, ")
                .append(DATE_FIELD)
                .append(" DATE, ")
                .append(TIME_FIELD)
                .append(" TIME)");
//                .append("FOREIGN KEY (")
//                .append(ID_PET_FIELD)
//                .append(") REFERENCES ")
//                .append(TABLE_NAME)
//                .append("(")
//                .append(ID_FIELD)
//                .append("))");

        sqLiteDatabase.execSQL(sql.toString());
        sqLiteDatabase.execSQL(medicineSql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // aktualizacja danych

    }

    public void addPetToDatabase(Pet pet)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, pet.getId());
        contentValues.put(NAME_FIELD, pet.getName());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void addMedicineToDatabase(Medicine medicine) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(ID_PET_FIELD, medicine.getId_pet());
        contentValues.put(MEDICINE_NAME_FIELD, medicine.getMedicineName());
        contentValues.put(MEDICINE_DESCRIPTION_FIELD, medicine.getMedicineDescription());
        contentValues.put(DATE_FIELD, medicine.getDate().toString());
        contentValues.put(TIME_FIELD, medicine.getTime().toString());

        sqLiteDatabase.insert(TABLE_NAME_MEDICINES, null, contentValues);
    }

    public void populatePetListArray() // pobiera zwierzeta z bazy danych i dodaje je do listy
    {
        SQLiteDatabase sqlLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqlLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while(result.moveToNext())
                {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    Pet pet = new Pet(id, name);
                    Pet.petArrayList.add(pet);
                }
            }
        }
    }

    public void populateMedicineListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_MEDICINES, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id_medicine = result.getInt(0);
                    //int id_pet = result.getInt(1);
                    String medicineName = result.getString(1);
                    String medicineDescription = result.getString(2);
                    Date date = parseDate(result.getString(3));
                    Time time = parseTime(result.getString(4));

                    Medicine medicine = new Medicine(id_medicine, medicineName, medicineDescription, date, time);
                    Medicine.medicineArrayList.add(medicine);
                }
            }
        }
    }


    private Date parseDate(String dateString) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Time parseTime(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            Date parsedDate = format.parse(timeString);
            return new Time(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePetInDatabase(Pet pet)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, pet.getId());
        contentValues.put(NAME_FIELD, pet.getName());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(pet.getId())});
    }

    public void updateMedicineInDatabase(Medicine medicine) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_MEDICINE_FIELD, medicine.getId_medicine());
      //  contentValues.put(ID_PET_FIELD, medicine.getId_pet());
        contentValues.put(MEDICINE_NAME_FIELD, medicine.getMedicineName());
        contentValues.put(MEDICINE_DESCRIPTION_FIELD, medicine.getMedicineDescription());
        contentValues.put(DATE_FIELD, formatDate(medicine.getDate())); // Przykładowa metoda formatująca datę
        contentValues.put(TIME_FIELD, formatTime(medicine.getTime())); // Przykładowa metoda formatująca czas

        sqLiteDatabase.update(TABLE_NAME_MEDICINES, contentValues, ID_MEDICINE_FIELD + " =? ", new String[]{String.valueOf(medicine.getId_medicine())});
    }


    private String formatDate(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    private String formatTime(Time time) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time);
    }

//    public void deletePetFromDatabase(int id) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String selection = ID_FIELD + " = ?";
//        String[] selectionArgs = { String.valueOf(id) };
//        sqLiteDatabase.delete(TABLE_NAME, selection, selectionArgs);
//    }

    public void deletePetFromDatabase(Pet pet) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID_FIELD + "=?", new String[] { String.valueOf(pet.getId()) });
        db.close();
    }


}