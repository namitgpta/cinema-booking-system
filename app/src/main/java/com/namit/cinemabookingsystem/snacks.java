package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.namit.cinemabookingsystem.adapter.payment_RecyclerViewAdapter;
import com.namit.cinemabookingsystem.adapter.snacks_RecyclerViewAdapter;

public class snacks extends AppCompatActivity {
    private RecyclerView recyclerViewSnacks;
    private snacks_RecyclerViewAdapter snacks_recyclerViewAdapter;
    public SQLiteDatabase myDb;
    public static Cursor c1;
    public static TextView total_amount_snacks;

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

        total_amount_snacks=findViewById(R.id.total_amount_snacks);
        String total_amount_snacks_str="Total Amount: Rs."+Seat_selection.amount;
        total_amount_snacks.setText(total_amount_snacks_str);
    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        c1=myDb.rawQuery("select id_str, name, price from snacks", null);
    }

    public void snacks_to_review_payment_intent(View view) {
        Intent intent = new Intent(snacks.this, review_payment.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if(c1!=null)
            c1.close();
        super.onDestroy();
    }
}