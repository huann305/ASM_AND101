package com.example.asm_huanvbph41609.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.asm_huanvbph41609.R;
import com.example.asm_huanvbph41609.model.PhongBan;
import com.example.asm_huanvbph41609.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class PhongBanAdapter extends BaseAdapter {

    List<PhongBan> list;
    Activity activity;
    List<PhongBan> listOld;

    public PhongBanAdapter(List<PhongBan> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.listOld = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.item_phong_ban, null);

        TextView textView = convertView.findViewById(R.id.name_phong_ban);

        textView.setText(list.get(position).getName());

        return convertView;
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()){
                    list = listOld;
                }else{
                    ArrayList<PhongBan> listS = new ArrayList<>();
                    for (PhongBan phongBan : listOld) {
                        if(phongBan.getName().toLowerCase().contains(s.toLowerCase())){
                            listS.add(phongBan);
                        }
                    }
                    list = listS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<PhongBan>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
