package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.namit.cinemabookingsystem.adapter.payment_RecyclerViewAdapter;

public class payment extends AppCompatActivity {
    private RecyclerView recyclerViewPayment;
    private payment_RecyclerViewAdapter payment_recyclerViewAdapter;
    public SQLiteDatabase myDb;
    public static Cursor c1;
    private TextView final_amount;

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

        final_amount=findViewById(R.id.amount_to_pay_final);
        String amount_str="Amount to be Paid: Rs."+review_payment.final_total_amount_to_be_paid;
        final_amount.setText(amount_str);
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