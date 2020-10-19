package com.namit.cinemabookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.namit.cinemabookingsystem.adapter.RecyclerViewAdapter;

public class book extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    public SQLiteDatabase myDb;
    public static Cursor c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initial();

        //setup adapter
        recyclerViewAdapter=new RecyclerViewAdapter(book.this, c1);

        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        c1=myDb.rawQuery("select f.full_name, room_id, start_time, f.name, ma.price, screenings._id from screenings " +
                        "join films f on f._id = screenings.film_id join movies_ads ma on screenings._id = ma.screening_id " +
                        "where film_id=?", new String[]{String.valueOf(InsideBody.current_counter)});
    }

    @Override
    protected void onDestroy() {
        if(c1!=null)
            c1.close();
        super.onDestroy();
    }
}