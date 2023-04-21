package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    String [] food = {"Норма", "Белки", "Жиры", "Углеводы"};
    String [] vyvod = {"Норма калорий: ", "Белки: ", "Жиры: ", "Углеводы: "};
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);
        tw = findViewById(R.id.kalor_logo);
        tw.setText("");
        for (int i = 0; i < 4; i++) {
            String s = sharedPreferences.getString(food[i], "No");
            tw.append(vyvod[i] + s + "\n");
        }

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.homeitem);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(MainActivity.this, DashBoard.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.homeitem:
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(MainActivity.this, Notification.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}