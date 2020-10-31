package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.namit.cinemabookingsystem.adapter.payment_RecyclerViewAdapter;
import com.namit.cinemabookingsystem.adapter.snacks_RecyclerViewAdapter;

public class snacks extends AppCompatActivity {
    private RecyclerView recyclerViewSnacks;
    private snacks_RecyclerViewAdapter snacks_recyclerViewAdapter;
    public SQLiteDatabase myDb;
    public static Cursor c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        recyclerViewSnacks =findViewById(R.id.recyclerView_snacks);
        recyclerViewSnacks.setHasFixedSize(true);
        recyclerViewSnacks.setLayoutManager(new LinearLayoutManager(this));
        initial();

        //setup adapter
        snacks_recyclerViewAdapter =new snacks_RecyclerViewAdapter(snacks.this, c1);

        recyclerViewSnacks.setAdapter(snacks_recyclerViewAdapter);
    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        c1=myDb.rawQuery("select id_str, name, price from snacks", null);
    }

    @Override
    protected void onDestroy() {
        if(c1!=null)
            c1.close();
        super.onDestroy();
    }
}