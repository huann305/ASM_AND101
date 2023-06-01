package com.example.asm_huanvbph41609.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_huanvbph41609.R;
import com.example.asm_huanvbph41609.model.Staff;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Staff> {
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Staff> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner, parent, false);
        ImageView imageView = convertView.findViewById(R.id.ivImg);

        Staff staff = this.getItem(position);
        if (staff !=null) imageView.setImageResource(staff.getImage());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner, parent, false);
        ImageView imageView = convertView.findViewById(R.id.ivImg);

        Staff staff = this.getItem(position);
        if (staff !=null) imageView.setImageResource(staff.getImage());
        return convertView;
    }
}
