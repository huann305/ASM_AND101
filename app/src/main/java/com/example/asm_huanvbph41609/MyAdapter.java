package com.example.asm_huanvbph41609;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Staff> staffs = new ArrayList<>();
    private Activity activity;

    public MyAdapter(ArrayList<Staff> staffs, Activity activity) {
        this.staffs = staffs;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return staffs.size();
    }

    @Override
    public Object getItem(int position) {
        return staffs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        convertView =inflater.inflate(R.layout.item_name, null);
        TextView tvId = (TextView) convertView.findViewById(R.id.txt_id);
        TextView tvName = (TextView) convertView.findViewById(R.id.txt_name);
        TextView tvRoom = (TextView) convertView.findViewById(R.id.txt_room);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgView);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);

        Staff staff = staffs.get(position);
        tvId.setText(staff.getId());
        tvName.setText(staff.getName());
        tvRoom.setText(staff.getRoom());
        imageView.setImageResource(staff.getImage());

        //Xóa
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffs.remove(position);
                notifyDataSetChanged();
            }
        });
        //Cập nhật
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return convertView;
    }

    public void openDialog(){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_add_staff);
        Window window = dialog.getWindow();
        if(window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}
