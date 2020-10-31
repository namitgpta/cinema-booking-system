package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.namit.cinemabookingsystem.adapter.snacks_RecyclerViewAdapter;

public class review_payment extends AppCompatActivity {
    public SQLiteDatabase myDb;
    public static Cursor c1;
    private TextView movie_name_textView, audi_number_textView, timing_textView, seats_booked_textView, snacks_booked_textView, total_amount_textView;
    private ImageView image_review_imageView;
    public static int final_total_amount_to_be_paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_payment);

        initial();
        movie_name_textView=findViewById(R.id.review_movie_name);
        audi_number_textView=findViewById(R.id.review_audi);
        timing_textView=findViewById(R.id.review_timing);
        seats_booked_textView=findViewById(R.id.review_seats_booked);
        snacks_booked_textView=findViewById(R.id.review_snacks_booked);
        total_amount_textView=findViewById(R.id.review_total_amount);
        image_review_imageView=findViewById(R.id.review_image);

        String movieName="Movie Name: "+c1.getString(1);
        movie_name_textView.setText(movieName);

        String audiName="Theatre Room: Audi "+c1.getString(3);
        audi_number_textView.setText(audiName);

        String timings="Starts at: "+c1.getString(4)+":00";
        timing_textView.setText(timings);

        String seatsBooked="Seats Booked: "+c1.getString(6);
        seats_booked_textView.setText(seatsBooked);

        String snacksBooked="Snacks Pre-ordered: "+c1.getString(8);
        snacks_booked_textView.setText(snacksBooked);

        final_total_amount_to_be_paid=c1.getInt(5);
        String totalFinalAmount_str="Total Amount: Rs."+final_total_amount_to_be_paid;
        total_amount_textView.setText(totalFinalAmount_str);

        String image_name=c1.getString(2)+"_portrait";
        int resId=getResources().getIdentifier(image_name, "raw", getPackageName());
        image_review_imageView.setBackgroundResource(resId);
    }

    public void initial(){
        myDb=DatabaseHelper.myDataBase;
        myDb.execSQL("update bookings set total_amount=?, seats_booked=?, snacks_booked=?",
                new String[]{String.valueOf(Seat_selection.amount), String.valueOf(Seat_selection.seats_booked), String.valueOf(snacks_RecyclerViewAdapter.snacks_booked)});
        c1=myDb.rawQuery("select * from bookings", null);
        c1.moveToFirst();
        //System.out.println("tatti: "+c1.getString(1)+" "+c1.getInt(5)+" "+c1.getString(6));
    }

    public void to_payment_page_intent(View view) {
        Intent intent=new Intent(review_payment.this, payment.class);
        startActivity(intent);
    }
}