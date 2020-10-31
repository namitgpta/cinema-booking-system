package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class decide_snacks extends AppCompatActivity {
    public SQLiteDatabase myDb;
    public Cursor c3;
    public static String total_amount_str;
    private TextView total_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_snacks);

        initial();
        total_amount=findViewById(R.id.total_amount_decide_snacks);
        String amount_str="Total Amount: Rs."+total_amount_str;
        total_amount.setText(amount_str);

    }

    public void initial(){
        myDb=DatabaseHelper.myDataBase;
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            total_amount_str=String.valueOf(bundle.getInt("total_amount"));
        }
    }

    public void yes_decideSnacks_to_snacks(View view) {
        Intent intent=new Intent(decide_snacks.this, snacks.class);
        startActivity(intent);
    }

    public void no_decideSnacks_to_reviewPayment(View view) {
        Intent intent=new Intent(decide_snacks.this, review_payment.class);
        startActivity(intent);
    }
}