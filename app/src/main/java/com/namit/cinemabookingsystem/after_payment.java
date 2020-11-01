package com.namit.cinemabookingsystem;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class after_payment extends AppCompatActivity {
    public SQLiteDatabase myDb;
    public static Cursor c1;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment);

        //firebase details:
        {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
//            String name = firebaseUser.getDisplayName();
                String email = firebaseUser.getEmail();
//            Uri photoUrl = firebaseUser.getPhotoUrl();
//            boolean emailVerified = firebaseUser.isEmailVerified();
//            String uid = firebaseUser.getUid();
//            Log.d("namitg677", "Here: " + name + "\n " + email + " " + photoUrl + " " + emailVerified + "\n" + uid);
            } else {
                Toast.makeText(after_payment.this, "Some Error has occurred:\nCheck Internet",
                        Toast.LENGTH_LONG).show();
            }
        }

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
        PdfDocument myPdfDocument=new PdfDocument();
        Paint myPaint=new Paint();

        PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(1000, 900, 1).create();
        PdfDocument.Page myPage=myPdfDocument.startPage(myPageInfo);
        Canvas canvas=myPage.getCanvas();

        myPaint.setTextSize(70);
        canvas.drawText("VIT Cinemas", 30, 80, myPaint);

        myPaint.setTextSize(30);
        canvas.drawText("Owned by: Namit Gupta & Aditya Beri", 30, 120, myPaint);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Invoice No.", canvas.getWidth()-40, 40, myPaint);
        canvas.drawText(String.valueOf(c1.getInt(0)), canvas.getWidth()-40, 80, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 150, canvas.getWidth()-30, 160, myPaint);

        myPaint.setColor(Color.BLACK);
        canvas.drawText("Date: ", 50, 200, myPaint);
        Cursor c_temp=myDb.rawQuery("select date()", null);
        c_temp.moveToFirst();
        canvas.drawText(c_temp.getString(0), 250, 200, myPaint);
        c_temp.close();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        Date date=new Date();
        canvas.drawText("Time: ", 620, 200, myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(formatter.format(date), canvas.getWidth()-40, 200, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 250, 250, 300, myPaint);

        myPaint.setColor(Color.WHITE);
        canvas.drawText("Bill To: ", 50, 285, myPaint);

        myPaint.setColor(Color.BLACK);
        canvas.drawText("Customer Email: ", 30, 350, myPaint);
        canvas.drawText(firebaseUser.getEmail(), 280, 350, myPaint);

        canvas.drawText("Phone: ", 620, 350, myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("1234567890", canvas.getWidth()-40, 350, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 400, canvas.getWidth()-30, 450, myPaint);

        myPaint.setColor(Color.WHITE);
        canvas.drawText("Movie", 50, 435, myPaint);
        canvas.drawText("Room", 350, 435, myPaint);
        canvas.drawText("Timings", 550, 435, myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Amount", canvas.getWidth()-40, 435, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setColor(Color.BLACK);
        canvas.drawText(c1.getString(1), 50, 480, myPaint);
        canvas.drawText("Audi "+c1.getString(3), 350, 480, myPaint);
        canvas.drawText(c1.getString(4)+":00", 550, 480, myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Rs."+c1.getString(5), canvas.getWidth()-40, 480, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 550, canvas.getWidth()-30, 560, myPaint);

        myPaint.setColor(Color.BLACK);
        canvas.drawText("Total Tickets: ", 550, 600, myPaint);
        canvas.drawText("Snacks: ", 550, 640, myPaint);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("TOTAL: ", 550, 680, myPaint);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(c1.getInt(6)), 970, 600, myPaint);
        canvas.drawText(String.valueOf(c1.getInt(8)), 970, 640, myPaint);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Rs." + c1.getInt(5), 970, 680, myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("\"Risk hai to Ishq hai\"", 30, 800, myPaint);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        canvas.drawText("Thank you very much. Come back again", 30, 840, myPaint);

        myPdfDocument.finishPage(myPage);
        String fileName="Cinema Booking "+ java.time.LocalDateTime.now() +".pdf";



        isStoragePermissionGranted();
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

//        try
//        {
//            outputFile.createNewFile();
//            OutputStream out = new FileOutputStream(outputFile);
//            myPdfDocument.writeTo(out);
//            myPdfDocument.close();
//            out.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

        //File file=new File(this.getExternalFilesDir("/"), fileName);

        try {
            myPdfDocument.writeTo(new FileOutputStream((outputFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myPdfDocument.close();

    }

    public void isStoragePermissionGranted() {
        String TAG = "Storage Permission";
        if (Build.VERSION.SDK_INT >= 28) {
            if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
        }
    }
}