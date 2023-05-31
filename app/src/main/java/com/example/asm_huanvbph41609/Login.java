package com.example.asm_huanvbph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);

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
                Intent i = getIntent();
                Bundle bundle = i.getExtras();
                if(TextUtils.isEmpty(edtUsername.getText().toString())){
                    edtUsername.setError("Chưa nhập tài khoản");
                    return;
                }
                if(TextUtils.isEmpty(edtPassword.getText().toString())){
                    edtPassword.setError("Chưa nhập mật khẩu");
                    return;
                }
                if(bundle == null){
                    Toast.makeText(Login.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }

                String user = bundle.getString("username");
                String pass = bundle.getString("password");


                boolean u = user.equals(edtUsername.getText().toString());
                boolean p = pass.equals(edtPassword.getText().toString());

                if(u && p){
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chính xác, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}