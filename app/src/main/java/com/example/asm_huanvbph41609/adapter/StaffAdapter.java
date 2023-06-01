package com.example.asm_huanvbph41609.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_huanvbph41609.R;
import com.example.asm_huanvbph41609.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffAdapter extends BaseAdapter {

    private ArrayList<Staff> staffs = new ArrayList<>();
    private Activity activity;

    public static int POS = -1;

    public StaffAdapter(ArrayList<Staff> staffs, Activity activity) {
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

        convertView = inflater.inflate(R.layout.item_card_staff, null);
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
                openDialog(position);
            }
        });

        return convertView;
    }

    public void openDialog(int positon){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_add_staff);
        Window window = dialog.getWindow();

        List<Staff> list = new ArrayList<>();
        Spinner spinner = dialog.findViewById(R.id.spinner);

        list.add(new Staff("", "", "", R.drawable.a));
        list.add(new Staff("", "", "",R.drawable.b));
        list.add(new Staff("", "", "", R.drawable.c));
        list.add(new Staff("", "", "", R.drawable.d));
        list.add(new Staff("", "", "", R.drawable.e));
        list.add(new Staff("", "", "", R.drawable.f));
        list.add(new Staff("", "", "", R.drawable.g));
        list.add(new Staff("", "", "", R.drawable.h));

        ItemAdapter itemAdapter = new ItemAdapter(window.getContext(), R.layout.layout_item_spinner, list);
        spinner.setAdapter(itemAdapter);
        spinner.setSelection(positon);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnEdit = dialog.findViewById(R.id.btn_add);
        EditText txtId = dialog.findViewById(R.id.txt_id_staff);
        EditText txtName = dialog.findViewById(R.id.txt_name_staff);
        EditText txtRoom = dialog.findViewById(R.id.txt_room_staff);



        Staff staff = staffs.get(positon);
        txtId.setText(staff.getId());
        txtName.setText(staff.getName());
        txtRoom.setText(staff.getRoom());

        btnEdit.setText("Edit");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Staff staffSelected = (Staff) spinner.getSelectedItem();
                int img = staffSelected.getImage();
                Staff s = staffs.get(positon);
                s.setId(txtId.getText().toString());
                s.setName(txtName.getText().toString());
                s.setRoom(txtRoom.getText().toString());
                s.setImage(img);
                Toast.makeText(activity, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });

        if(window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}
