package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class Registration extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    //ключи
    String [] key = {"Первый", "Второй", "Третий", "Дата", "Завтрак", "Обед", "Ужин"};
    String [] food = {"Норма", "Белки", "Жиры", "Углеводы"};
    String [] info = {"Имя", "Вес", "Рост", "Пол", "Возраст"};
    Button reg;
    EditText name, massa, height, gender, date;
    int birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);

        name = findViewById(R.id.editText_name);
        massa = findViewById(R.id.editText_massa);
        height = findViewById(R.id.editText_height);
        gender = findViewById(R.id.editText_gender);
        date = findViewById(R.id.editText_Date);

        reg = findViewById(R.id.reg);
        //Number.class.isPrimitive();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(info[0], name.getText().toString());
                editor.putString(info[1], massa.getText().toString());
                editor.putString(info[2], height.getText().toString());
                editor.putString(info[3], gender.getText().toString());
                editor.putString(info[4], date.getText().toString());
                OldDate();
                for (String s : key) {
                    editor.putString(s, Integer.toString(0));
                }
                Norma();
                editor.commit();
                Intent i = new Intent(Registration.this, MainActivity.class);
                startActivity(i);
                finish();
                //String Enter = name.getText().toString();
                //String massaEnter = massa.getText().toString();
                //String heightEnter = height.getText().toString();

            }
        });
    }

    //высчитывем возраст
    public void OldDate()
    {
        String [] s = date.getText().toString().split("\\.");
        Date date = new Date(Integer.parseInt(s[2]), Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
        Date now = new Date();
        birth = (now.getYear() + 1900) - date.getYear();
        System.out.println(date.getYear() + " " + (now.getYear() + 1900));
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
    //высчитываем норму КБЖУ
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