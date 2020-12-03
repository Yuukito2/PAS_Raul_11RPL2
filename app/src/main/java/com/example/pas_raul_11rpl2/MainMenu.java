package com.example.pas_raul_11rpl2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class MainMenu extends AppCompatActivity {

    CardView menu1;
    CardView menu2;
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        menu1 = (CardView)findViewById(R.id.menu1);
        menu2 = (CardView)findViewById(R.id.menu2);

        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);

        String username;
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            username = "";

        }else{
            username = extras.getString("Username");
        }

        //Menu 1
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListData.class));
            }
        });

        //Menu 2
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListDataFavourite.class));
            }
        });


    }
}