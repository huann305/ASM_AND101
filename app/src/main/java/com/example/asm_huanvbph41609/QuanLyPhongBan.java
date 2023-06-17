package com.example.asm_huanvbph41609;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asm_huanvbph41609.adapter.PhongBanAdapter;
import com.example.asm_huanvbph41609.model.PhongBan;

import java.util.ArrayList;
import java.util.List;

public class QuanLyPhongBan extends AppCompatActivity {
    Toolbar toolbar;
    PhongBanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanliphongban);

        ListView listView = findViewById(R.id.listview_phong_ban);

        List<PhongBan> list = new ArrayList<>();

        list.add(new PhongBan("Đào tạo"));
        list.add(new PhongBan("Hành chính"));
        list.add(new PhongBan("Nhân sự"));
        list.add(new PhongBan("Kế toán"));

        adapter = new PhongBanAdapter(list, this);
        listView.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbarPhongBan);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_phong_ban, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search_toolbar).getActionView();

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_search_24));

        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHint("Nhập phòng ban cần tìm");
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));

        searchView = (SearchView) menu.findItem(R.id.item_search_toolbar).getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setNavigationIcon(null);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Lấy dữ liệu khi người dùng nhấn nút tìm kiếm
                Toast.makeText(QuanLyPhongBan.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý sự kiện khi người dùng nhập liệu vào SearchView
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}