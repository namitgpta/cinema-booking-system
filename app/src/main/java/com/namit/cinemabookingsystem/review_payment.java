package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class review_payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_payment);

        initial();

    }

    public void initial(){

    }

    public void to_payment_page_intent(View view) {
        Intent intent=new Intent(review_payment.this, payment.class);
        startActivity(intent);
    }
}