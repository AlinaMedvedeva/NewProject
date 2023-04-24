package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class AddActivity extends AppCompatActivity {

    public static final String ADDKEY = "Hello";
    MyDataBase myDataBase;
    SQLiteDatabase sdb;
    Cursor cursor;
    EditText search;
    ListView lw;
    Button addProduct;
    LinkedList<HashMap<String, Object>> adapterProductList = new LinkedList<>();
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String s = getIntent().getStringExtra(MainActivity.KEY);

        search = findViewById(R.id.search);
        lw = findViewById(R.id.list_products);
        addProduct = findViewById(R.id.addProducts);

        myDataBase = new MyDataBase(this);
        try{
            myDataBase.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        try{
            sdb = myDataBase.getWritableDatabase();
        }catch (SQLException mSQLException)
        {
            throw mSQLException;
        }
        cursor = sdb.rawQuery("SELECT * FROM table_kalor", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", cursor.getString(0));
            adapterProductList.add(map);
            cursor.moveToNext();
        }
        cursor.close();
        String [] from = {"name"};
        int [] to = {R.id.name_info};
        adapter = new SimpleAdapter(this, adapterProductList, R.layout.list_item, from, to);
        lw.setAdapter(adapter);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AddActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}