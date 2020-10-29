package com.namit.cinemabookingsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Seat_selection extends AppCompatActivity {
    public int screening_id;
    public SQLiteDatabase myDb;
    public Cursor c2;
    private TextView seatSelection_heading, total_amount, seatSelection_timings;
    private Button proceed;
    public int amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        initial();
        seatSelection_heading=findViewById(R.id.seat_selection_heading);
        c2.moveToFirst();
        String heading_text=c2.getString(5)+": "+c2.getString(6);
        seatSelection_heading.setText(heading_text);

        proceed=findViewById(R.id.proceed_seat_selection);
        total_amount=findViewById(R.id.amount_seat_selection);
        seatSelection_timings=findViewById(R.id.timings_seat_selection);
        amount=c2.getInt(7);
        String amount_str="Total Amount: Rs."+amount;
        total_amount.setText(amount_str);




        //Toast.makeText(Seat_selection.this, "OnCreate called", Toast.LENGTH_SHORT).show();
    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String id_str=bundle.getString("id");
            if(id_str!=null){
                screening_id=Integer.parseInt(id_str);
            }
        }
        c2=myDb.rawQuery("select room_id, start_time, film_id, seats_full, r.total_seats, r.name, f.full_name, ma.price from screenings \n" +
                "join rooms r on screenings._id = r._id\n" +
                "join films f on f._id = screenings.film_id\n" +
                "join movies_ads ma on screenings._id = ma.screening_id where screenings._id=?;", new String[]{String.valueOf(screening_id)});
    }

    public void seat_to_decideSnacks_intent_change(View view) {
        Intent intent=new Intent(Seat_selection.this, decide_snacks.class);
        intent.putExtra("total_amount", amount);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if(c2!=null)
            c2.close();
        super.onDestroy();
        //Toast.makeText(Seat_selection.this, "ondestroy called", Toast.LENGTH_SHORT).show();
    }













    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(Seat_selection.this, "onstart called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //Toast.makeText(Seat_selection.this, "onpostresume called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(Seat_selection.this, "onpause called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(Seat_selection.this, "onstop called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(Seat_selection.this, "onrestart called", Toast.LENGTH_SHORT).show();
    }
}