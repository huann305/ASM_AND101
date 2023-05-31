package com.example.asm_huanvbph41609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        Button btnHadAcc = findViewById(R.id.btnHadAcc);
        Button btnRegester = findViewById(R.id.btnRegester);
        EditText edtUsername2 = findViewById(R.id.edtUsername2);
        EditText edtPassword2 = findViewById(R.id.edtPassword2);
        EditText edtConfirmPass = findViewById(R.id.edtConfirmPass);

        btnHadAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regester.this, Login.class);
                startActivity(intent);
            }
        });

        btnRegester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername2.getText().toString();
                String password = edtPassword2.getText().toString();
                String confirm = edtConfirmPass.getText().toString();


                if (username.trim().equals("")) {
                    edtUsername2.setError("Tên đăng nhập không được để trống");
                    return;
                }
                if (password.trim().equals("")) {
                    edtPassword2.setError("Mật khẩu không được để trống");
                    return;
                }

                if (!username.matches("[\\w]+")) {
                    edtUsername2.setError("Tên đăng nhập không hợp lệ");
                    return;
                }
                if (!password.matches("[\\w]+") && password.trim().equals("")) {
                    edtPassword2.setError("Mật khẩu không hợp lệ");
                    return;
                }
                if (!password.equals(confirm)) {
                    edtConfirmPass.setError("Mật khẩu không trùng khớp");
                    return;
                }

                Intent intent = new Intent(Regester.this, Login.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("password", password);
                intent.putExtras(bundle);
                Toast.makeText(Regester.this, "Đăng ký thành công, vui lòng đăng nhập để tiếp tục", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}