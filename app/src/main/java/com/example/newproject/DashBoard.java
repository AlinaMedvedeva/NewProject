package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    String [] info = {"Имя", "Вес", "Рост", "Пол", "Возраст"};
    Button edit_bt;
    TextView name, massa, height, date, gender;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        name = findViewById(R.id.info_name);
        massa = findViewById(R.id.info_massa);
        height = findViewById(R.id.info_height);
        date = findViewById(R.id.info_date);
        gender = findViewById(R.id.info_gender);
        edit_bt = findViewById(R.id.edit_bt);
        sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);
        name.append(sharedPreferences.getString(info[0], "no"));
        massa.append(sharedPreferences.getString(info[1], "no"));
        height.append(sharedPreferences.getString(info[2], "no"));
        gender.append(sharedPreferences.getString(info[3], "no"));
        date.append(sharedPreferences.getString(info[4], "no"));


        edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard.this, EditActivity.class);
                startActivity(i);
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.dashboard:overridePendingTransition(0, 0);
                        return true;
                    case R.id.homeitem:
                        startActivity(new Intent(DashBoard.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(DashBoard.this, Notification.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}