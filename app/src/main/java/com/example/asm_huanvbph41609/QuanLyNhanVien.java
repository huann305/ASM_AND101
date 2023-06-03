package com.example.asm_huanvbph41609;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.asm_huanvbph41609.adapter.ItemAdapter;
import com.example.asm_huanvbph41609.adapter.StaffAdapter;
import com.example.asm_huanvbph41609.model.Staff;
import com.example.asm_huanvbph41609.validate.Validate;

import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVien extends AppCompatActivity {

    private Staff staff;

    int itemPosition = 0;
    StaffAdapter myAdapter;
    Spinner spinner;

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlinhanvien);

        listView = findViewById(R.id.lstStaff);
        Button btnAddStaff = findViewById(R.id.btnAddStaff);

        Toolbar toolbar = findViewById(R.id.toolbar_staff);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_24);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        ArrayList<Staff> staffs = new ArrayList<>();
        staffs.add(new Staff("NV001", "Vũ Bá Huấn", "Đào tạo", R.drawable.a));
        staffs.add(new Staff("NV002", "Nguyễn Văn An", "Kế toán",R.drawable.b));
        staffs.add(new Staff("NV003", "Nguyễn Văn A", "Hành chính", R.drawable.c));
        staffs.add(new Staff("NV004", "Trần Thị B", "Đào tạo", R.drawable.d));
        staffs.add(new Staff("NV005", "Phạm Thị C", "Hành chính", R.drawable.e));
        staffs.add(new Staff("NV006", "Lê Văn D", "Đào tạo", R.drawable.f));
        staffs.add(new Staff("NV007", "Nghiêm Thị E", "Kế toán", R.drawable.g));
        staffs.add(new Staff("NV008", "Phí Văn F", "Hành chính", R.drawable.h));

        myAdapter = new StaffAdapter(staffs, this);
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
                EditText txtId = dialog.findViewById(R.id.txt_id_staff);
                EditText txtName = dialog.findViewById(R.id.txt_name_staff);
                EditText txtRoom = dialog.findViewById(R.id.txt_room_staff);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearForm(txtId, txtName, txtRoom);
                        dialog.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = txtId.getText().toString();
                        String name = txtName.getText().toString();
                        String room = txtRoom.getText().toString();
                        Staff staffSelected = (Staff) spinner.getSelectedItem();
                        int img = staffSelected.getImage();

                        if(!Validate.validate(id, name, room)){
                            Toast.makeText(QuanLyNhanVien.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        staffs.add(new Staff(id, name, room, img));

                        StaffAdapter myAdapter = new StaffAdapter(staffs, QuanLyNhanVien.this);
                        listView.setAdapter(myAdapter);
                        clearForm(txtId, txtName, txtRoom);
                        dialog.cancel();
                        Toast.makeText(QuanLyNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        spinner = dialog.findViewById(R.id.spinner);

        List<Staff> list = new ArrayList<>();

        list.add(new Staff("", "", "", R.drawable.a));
        list.add(new Staff("", "", "",R.drawable.b));
        list.add(new Staff("", "", "", R.drawable.c));
        list.add(new Staff("", "", "", R.drawable.d));
        list.add(new Staff("", "", "", R.drawable.e));
        list.add(new Staff("", "", "", R.drawable.f));
        list.add(new Staff("", "", "", R.drawable.g));
        list.add(new Staff("", "", "", R.drawable.h));

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.layout_item_spinner, list);
        spinner.setAdapter(itemAdapter);



    }

    public void clearForm(EditText txtId, EditText txtName, EditText txtRoom){
        txtId.setText("");
        txtName.setText("");
        txtRoom.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_toolbar).getActionView();

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_search_24));

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search_toolbar){

        }
        return super.onOptionsItemSelected(item);
    }
}