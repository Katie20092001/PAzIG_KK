package com.example.probatrzecia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MedicineAdapter extends ArrayAdapter<Medicine>
{

    public MedicineAdapter(Context context, List<Medicine> medicines)
    {
        super(context, 0, medicines);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Medicine medicines = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medicine_cell, parent, false);

        TextView medicineName = convertView.findViewById(R.id.cellNameMedicine);
        TextView description = convertView.findViewById(R.id.cellNameMedicineDesc);


        medicineName.setText(medicines.getMedicineName());
        description.setText(medicines.getMedicineDescription());

        return convertView;
    }

}
