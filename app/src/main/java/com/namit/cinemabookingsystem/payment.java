package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.namit.cinemabookingsystem.adapter.payment_RecyclerViewAdapter;

public class payment extends AppCompatActivity {
    private RecyclerView recyclerViewPayment;
    private payment_RecyclerViewAdapter payment_recyclerViewAdapter;
    public SQLiteDatabase myDb;
    public static Cursor c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        recyclerViewPayment =findViewById(R.id.recyclerView_payment);
        recyclerViewPayment.setHasFixedSize(true);
        recyclerViewPayment.setLayoutManager(new LinearLayoutManager(this));
        initial();

        //setup adapter
        payment_recyclerViewAdapter=new payment_RecyclerViewAdapter(payment.this, c1);

        recyclerViewPayment.setAdapter(payment_recyclerViewAdapter);
    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        c1=myDb.rawQuery("select id_str, name from payment_methods", null);
    }

    @Override
    protected void onDestroy() {
        if(c1!=null)
            c1.close();
        super.onDestroy();
    }
}