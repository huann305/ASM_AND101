package com.example.asm_huanvbph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class QuanLyNhanVien extends AppCompatActivity {

    private Staff staff;

    int itemPosition = 0;
    MyAdapter myAdapter;

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlinhanvien);

        listView = findViewById(R.id.lstStaff);
        Button btnAddStaff = findViewById(R.id.btnAddStaff);

        ArrayList<Staff> staffs = new ArrayList<>();
        staffs.add(new Staff("NV001", "Vũ Bá Huấn", "Nhân sự", R.drawable.a));
        staffs.add(new Staff("NV002", "Vũ Bá A", "Kế toán",R.drawable.b));
        staffs.add(new Staff("NV003", "Vũ Bá C", "Hành chính", R.drawable.c));
        staffs.add(new Staff("NV004", "Vũ Bá Huấn", "Đào tạo", R.drawable.d));
        staffs.add(new Staff("NV005", "Vũ Bá Huấn", "Hành chính", R.drawable.e));
        staffs.add(new Staff("NV006", "Vũ Bá Huấn", "Đào tạo", R.drawable.f));
        staffs.add(new Staff("NV007", "Vũ Bá Huấn", "Kế toán", R.drawable.g));
        staffs.add(new Staff("NV008", "Vũ Bá Huấn", "Hành chính", R.drawable.h));

        myAdapter = new MyAdapter(staffs, this);
        listView.setAdapter(myAdapter);


        Dialog dialog = new Dialog(QuanLyNhanVien.this);

        dialog.setContentView(R.layout.activity_add_staff);
        Window window = dialog.getWindow();
        if(window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                Button btnAdd = dialog.findViewById(R.id.btn_add);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText txtId = dialog.findViewById(R.id.txt_id_staff);
                        EditText txtName = dialog.findViewById(R.id.txt_name_staff);
                        EditText txtRoom = dialog.findViewById(R.id.txt_room_staff);

                        String id = txtId.getText().toString();
                        String name = txtName.getText().toString();
                        String room = txtRoom.getText().toString();

                        staffs.add(new Staff(id, name, room, R.drawable.d));

                        MyAdapter myAdapter = new MyAdapter(staffs, QuanLyNhanVien.this);
                        listView.setAdapter(myAdapter);
                        dialog.cancel();
                        Toast.makeText(QuanLyNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}