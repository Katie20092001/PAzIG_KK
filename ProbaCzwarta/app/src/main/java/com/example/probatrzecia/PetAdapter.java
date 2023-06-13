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

public class PetAdapter extends ArrayAdapter<Pet>
{
    public PetAdapter(Context context, List<Pet> pets)
    {
        super(context, 0, pets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Pet pets = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.name_cell, parent, false);

        TextView name = convertView.findViewById(R.id.cellName);

        name.setText(pets.getName());

            return convertView;
    }

}
