package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class EditActivity extends AppCompatActivity {

    String [] info = {"Имя", "Пол", "Возраст", "Вес", "Рост"};
    String [] key = {"Первый", "Второй", "Третий", "Дата", "Завтрак", "Обед", "Ужин"};
    String [] food = {"Норма", "Белки", "Жиры", "Углеводы"};
    SharedPreferences sharedPreferences;
    EditText name, gender, date, massa, height;
    Button save;
    int birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);

        name = findViewById(R.id.edit_name);
        gender = findViewById(R.id.edit_gender);
        date = findViewById(R.id.edit_date);
        massa = findViewById(R.id.edit_massa);
        height = findViewById(R.id.edit_height);
        save = findViewById(R.id.edit_save);
        EditText [] et = {name, gender, date, massa, height};
        int k = 0;
        for (EditText editText: et) {
            editText.setText(sharedPreferences.getString(info[k], "no"));
            k++;
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                OldDate();
                Norma();
                editor.putString(info[0], name.getText().toString());
                editor.putString(info[1], gender.getText().toString());
                editor.putString(info[2], date.getText().toString());
                editor.putString(info[3], massa.getText().toString());
                editor.putString(info[4], height.getText().toString());
                editor.commit();
                Intent i = new Intent(EditActivity.this, DashBoard.class);
                startActivity(i);
                //if(!gender.getText().toString().equals(sharedPreferences.getString(info[1], "no")))
                //{

                //}
            }
        });
    }
    public void OldDate()
    {
        String [] s = date.getText().toString().split("\\.");
        Date date = new Date(Integer.parseInt(s[2]), Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
        Date now = new Date();
        birth = (now.getYear() + 1900) - date.getYear();
        if(date.getMonth() > now.getMonth())
        {
            birth--;
        }
        if(date.getMonth() == now.getMonth())
        {
            if(date.getDay() > now.getDay())
                birth--;
        }
    }
    public void Norma()
    {
        String gen = gender.getText().toString();
        int mas = Integer.parseInt(massa.getText().toString());
        int hegh = Integer.parseInt(height.getText().toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(gen.charAt(0) == 'Ж' || gen.charAt(0) == 'ж')
        {
            double n = (447.593 + (9.247*mas) + (3.098*hegh)) - (4.33*birth)*1.2;
            editor.putString(food[0], Double.toString(n));
        }
        else {
            double n = (88.362 + (13.397*mas)+(3.098*hegh)-(4.33*birth)*1.2);
            editor.putString(food[0], Double.toString(n));
        }

        editor.putString(food[1], Double.toString(1.7*mas));
        editor.putString(food[2], Double.toString(1.1*mas));
        editor.putString(food[3], Double.toString(2*mas));
        editor.commit();
    }
}