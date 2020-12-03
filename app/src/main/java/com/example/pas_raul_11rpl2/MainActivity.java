package com.example.pas_raul_11rpl2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText txt_Username;
    EditText txt_Password;
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("Login", MODE_PRIVATE);

        txt_Username = (EditText)findViewById(R.id.txt_Username);
        txt_Password = (EditText)findViewById(R.id.txt_Password);
        btn_Login = (Button)findViewById(R.id.btn_Login);

        btn_Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (txt_Username.getText().toString()!=null && txt_Password.getText().toString()!=null) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    String strName = null;
                    intent.putExtra("Username", strName);

                    editor = pref.edit();
                    editor.putString("Username", txt_Username.getText().toString());
                    editor.apply();
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
}