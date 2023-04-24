package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    //меню с фрагментами
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    public static final String KEY = "Hello";
    //ключи
    String [] food = {"Норма", "Белки", "Жиры", "Углеводы"};
    String [] key = {"Первый", "Второй", "Третий", "Дата", "Завтрак", "Обед", "Ужин"};
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);
        tw = findViewById(R.id.kalor_logo);
        tw.setText("");
        //for (int i = 0; i < 4; i++) {
           // String s = sharedPreferences.getString(food[i], "No");
          //  tw.append(vyvod[i] + s + "\n");
        //}

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.homeitem);
        //переключаемся между активностями
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

    public void addBreakfast(View view) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        i.putExtra(KEY, "Завтрак");
        startActivity(i);
    }

    public void addLunch(View view) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        i.putExtra(KEY, "Обед");
        startActivity(i);
    }

    public void addDinner(View view) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        i.putExtra(KEY, "Ужин");
        startActivity(i);
    }
}