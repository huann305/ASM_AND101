package com.example.asm_huanvbph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_huanvbph41609.model.Account;
import com.example.asm_huanvbph41609.service.XFile;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    List<Account> list;
    CheckBox chkRemember;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chkRemember = findViewById(R.id.chkRemember);

        list = XFile.readFileAcc(this, XFile.FileAcc);

        if (list.isEmpty()) {
            list = new ArrayList<>();
        }

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegester = findViewById(R.id.btnRegester);

        btnRegester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Regester.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                boolean check2 = false;
                if (TextUtils.isEmpty(edtUsername.getText().toString())) {
                    edtUsername.setError("Chưa nhập tài khoản");
                    return;
                }
                if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    edtPassword.setError("Chưa nhập mật khẩu");
                    return;
                }
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();

                for (Account acc : list) {
                    if (acc.getUsername().equals(user)) {
                        check2 = true;
                    }
                    if (acc.getUsername().equals(user) && acc.getPassword().equals(pass)) {
                        check = true;
                        break;
                    }
                }

                if (!check2) {
                    Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (check) {
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkRemember();

    }

    @Override
    protected void onPause() {
        super.onPause();
        remember(edtUsername.getText().toString(), edtPassword.getText().toString(), chkRemember.isChecked());
    }

    public void remember(String user, String pass, boolean chkRemember) {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putBoolean("chkRemember", chkRemember);

        editor.apply();
    }

    public void checkRemember() {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean chkRemember = sharedPreferences.getBoolean("chkRemember", false);

        this.chkRemember.setChecked(chkRemember);
        if (this.chkRemember.isChecked()) {
            edtUsername.setText(user);
            edtPassword.setText(pass);
        }
    }
}