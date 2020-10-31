package com.namit.cinemabookingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class after_payment extends AppCompatActivity {
    public SQLiteDatabase myDb;
    public static Cursor c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment);

        initial();
    }

    private void initial(){
        myDb=DatabaseHelper.myDataBase;
        c1=myDb.rawQuery("select * from bookings", null);
        c1.moveToFirst();
    }

    public void imageView_download(View view) {
        printInvoice();
    }

    public void printInvoice(){
        PdfDocument pdfDocument=new PdfDocument();
        Paint myPaint=new Paint();


    }
}