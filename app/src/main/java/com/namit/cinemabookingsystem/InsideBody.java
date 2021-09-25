package com.namit.cinemabookingsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.MovieModel;
import com.amplifyframework.datastore.generated.model.UsersModel;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class InsideBody extends AppCompatActivity {

    public final int MAX_MOVIES_ALLOWED = 10;
    public static int NO_OF_MOVIES_FETCHED = 0;

    private FirebaseUser firebaseUser;
    DatabaseHelper myDbHelper;
    public SQLiteDatabase myDb;
    /*
    private StorageReference mStorageRef;
    @SuppressLint("SdCardPath")
    Uri file = Uri.fromFile(new File("/data/data/com.namit.cinemabookingsystem/databases/cinema.db"));
    StorageReference riversRef;
    */

    private ImageView movies_imageView;
    private TextView counter_textView;
    public static int current_counter = 0;
    private String[] moviePathName_list, movies_title_list, movieReleaseDate_list, movieDuration_list, movieRating_list, movieExternalLink_list;
    private TextView film_heading_textView, release_date_textView, filmDuration_textView, filmRating_textView;
    private TextView greetingTextView;

    //@SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_body);

        {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                String name = firebaseUser.getDisplayName();
                String email = firebaseUser.getEmail();
                Uri photoUrl = firebaseUser.getPhotoUrl();
                boolean emailVerified = firebaseUser.isEmailVerified();
                String uid = firebaseUser.getUid();
                Log.d("namitg677", "Here: " + name + "\n " + email + " " + photoUrl + " " + emailVerified + "\n" + uid);
            } else {
                Toast.makeText(InsideBody.this, "Some Error has occurred:\nCheck Internet",
                        Toast.LENGTH_LONG).show();
            }

            myDbHelper = new DatabaseHelper(InsideBody.this);
            try {
                myDbHelper.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }
            myDbHelper.openDataBase();
            //Toast.makeText(InsideBody.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
            myDb = DatabaseHelper.myDataBase;
        } // firebase user and Database connection details

        greetingTextView = findViewById(R.id.greeting);

        //mStorageRef = FirebaseStorage.getInstance().getReference();
        initial();

        ImageButton nextButton = findViewById(R.id.next_imageButton);
        ImageButton prevButton = findViewById(R.id.prev_imageButton);
        movies_imageView = findViewById(R.id.imageView_movies);
        counter_textView = findViewById(R.id.counter_movies);
        film_heading_textView = findViewById(R.id.film_name);
        release_date_textView = findViewById(R.id.release_date_textview);
        filmDuration_textView = findViewById(R.id.duration_textview);
        filmRating_textView = findViewById(R.id.rating_textview);

        moviePathName_list = new String[MAX_MOVIES_ALLOWED];
        movies_title_list = new String[MAX_MOVIES_ALLOWED];
        movieReleaseDate_list = new String[MAX_MOVIES_ALLOWED];
        movieDuration_list = new String[MAX_MOVIES_ALLOWED];
        movieRating_list = new String[MAX_MOVIES_ALLOWED];
        movieExternalLink_list = new String[MAX_MOVIES_ALLOWED];

        //AWS Amplify DataStore movies fetching
        Amplify.DataStore.query(
                MovieModel.class,
                items -> {

                    while (items.hasNext()) {
                        MovieModel item = items.next();
                        movies_title_list[NO_OF_MOVIES_FETCHED] = item.getTitle();
                        movieDuration_list[NO_OF_MOVIES_FETCHED] = item.getDuration();
                        movieRating_list[NO_OF_MOVIES_FETCHED] = item.getRating();
                        movieReleaseDate_list[NO_OF_MOVIES_FETCHED] = item.getReleaseDate();
                        moviePathName_list[NO_OF_MOVIES_FETCHED] = item.getPathName();
                        movieExternalLink_list[NO_OF_MOVIES_FETCHED] = item.getExternalLink();

                        Log.i("Amplify", "Id " + item.getId() + " ,Movie " + item.getTitle());
                        NO_OF_MOVIES_FETCHED++;
                    }

                    String counter_initial_text = current_counter + 1 + " out of " + NO_OF_MOVIES_FETCHED;
                    counter_textView.setText(counter_initial_text);

                    int resId = getResources().getIdentifier(moviePathName_list[current_counter], "raw", getPackageName());
                    movies_imageView.setBackgroundResource(resId);
                    set_movie_details(current_counter);
                },
                failure -> Log.e("Amplify", "Could not query DataStore: movies list", failure)
        );


//        try (Cursor c1 = myDb.rawQuery("select distinct name from films where _id in (select film_id from screenings where _id in (select screening_id from movies_ads))", null);
//             Cursor c2 = myDb.rawQuery("select * from films", null)) {
//            String counter_initial_text = current_counter + " out of " + c1.getCount();
//            counter_textView.setText(counter_initial_text);
//            moviePathName_list = new String[c1.getCount()];
//            movies_title_list = new String[c1.getCount()];
//            movieReleaseDate_list = new String[c1.getCount()];
//            movieDuration_list = new String[c1.getCount()];
//            movieRating_list = new String[c1.getCount()];
//            int i = 0;
//            while (c1.moveToNext()) {
//                moviePathName_list[i++] = c1.getString(0);
//            }
//            int resId = getResources().getIdentifier(moviePathName_list[0], "raw", getPackageName());
//            movies_imageView.setBackgroundResource(resId);
//
//            i = 0;
//            while (c2.moveToNext()) {
//                movies_title_list[i] = c2.getString(3);
//                movieReleaseDate_list[i] = c2.getString(4);
//                movieDuration_list[i] = c2.getString(2);
//                movieRating_list[i++] = c2.getString(5);
//            }
//            set_movie_details(0);
//        }

        nextButton.setOnClickListener(v -> {
            if (current_counter + 1 < NO_OF_MOVIES_FETCHED) {
                current_counter++;
                String ctr_text = current_counter + 1 + " out of " + NO_OF_MOVIES_FETCHED;
                counter_textView.setText(ctr_text);
                int resId = getResources().getIdentifier(moviePathName_list[current_counter], "raw", getPackageName());
                movies_imageView.setBackgroundResource(resId);

                set_movie_details(current_counter);
            }

        });
        prevButton.setOnClickListener(v -> {
            if (current_counter > 0) {
                current_counter--;
                String ctr_text = current_counter + 1 + " out of " + NO_OF_MOVIES_FETCHED;
                counter_textView.setText(ctr_text);
                int resId = getResources().getIdentifier(moviePathName_list[current_counter], "raw", getPackageName());
                movies_imageView.setBackgroundResource(resId);

                set_movie_details(current_counter);
            }

        });


    }

    private void initial() {
//        myDb.execSQL("update customers set _id=?", new String[]{firebaseUser.getUid()});
//        myDb.execSQL("update customers set email=?", new String[]{firebaseUser.getEmail()});
        String nameFromSignUp = getIntent().getStringExtra("name");
        //String phone = getIntent().getStringExtra("phone");
//        if (name != null && phone != null) {
//            myDb.execSQL("update customers set name=?", new String[]{name});
//            myDb.execSQL("update customers set phone=?", new String[]{phone});
//        }

        //AWS Amplify
        //User details checking
        String firebaseUid = firebaseUser.getUid();

        if (nameFromSignUp == null) {
            //from login:

            Amplify.DataStore.query(
                    UsersModel.class, Where.matches(UsersModel.FIREBASE_ID.eq(firebaseUid)),
                    items -> {
                        String awsEmail = null;
                        String awsName = null;

                        while (items.hasNext()) {
                            UsersModel item = items.next();
                            Log.i("Amplify", "Id " + item.getId());
                            awsEmail = item.getEmail();
                            awsName = item.getNameOfUser();
                        }

                        String greet = "Hello, " + awsName + " (" + awsEmail + ")";
                        greetingTextView.setText(greet);
                    },
                    failure -> Log.e("Amplify", "Could not query DataStore //from login", failure)
            );
        } else {
            //from signup:

            String awsEmail = firebaseUser.getEmail();
            // Add to AWS Amplify DataStore
            UsersModel item = UsersModel.builder()
                    .firebaseId(firebaseUid)
                    .location("")
                    .email(awsEmail)
                    .nameOfUser(nameFromSignUp)
                    .phone(getIntent().getStringExtra("phone"))
                    .build();
            Amplify.DataStore.save(
                    item,
                    success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                    error -> Log.e("Amplify", "Could not save item to DataStore //from signup", error)
            );

            String greet = "Hello, " + nameFromSignUp + " (" + awsEmail + ")";
            greetingTextView.setText(greet);
        }
    }

    public void movie_imageView_external_click(View view) {
//        try (Cursor c1 = myDb.rawQuery("select link from imdb_links where film_id=?", new String[]{String.valueOf(current_counter)})) {
//            c1.moveToFirst();
//            Intent external_link = new Intent(Intent.ACTION_VIEW, Uri.parse(c1.getString(0)));
//            startActivity(external_link);
//        }

        Intent external_link = new Intent(Intent.ACTION_VIEW, Uri.parse(movieExternalLink_list[current_counter]));
        startActivity(external_link);
    }

    private void set_movie_details(int index) {
        film_heading_textView.setText(movies_title_list[index]);
        String release_date = "Release Date: " + movieReleaseDate_list[index];
        release_date_textView.setText(release_date);
        String duration = "Duration: " + movieDuration_list[index] + " hrs";
        filmDuration_textView.setText(duration);
        String rating = "Rating: " + movieRating_list[index];
        filmRating_textView.setText(rating);
    }

    public void book_layout_intent_change(View view) {
        Intent intent = new Intent(InsideBody.this, book.class);
        startActivity(intent);
    }

    public void logout_method(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(InsideBody.this, MainActivity.class));
        Toast.makeText(InsideBody.this, "Logged out", Toast.LENGTH_SHORT).show();
        //Amplify.DataStore.clear(()->Log.i("Amplify", "Datastore cleared"), failure->Log.e("Amplify", "Failed to clear"));
        finish();
    }

    @Override
    protected void onStop() {
        //download();
        super.onStop();
    }

//    public void upload() {
//        riversRef = mStorageRef.child("DBs/cinema.db");
//        riversRef.putFile(file)
//                .addOnSuccessListener(taskSnapshot -> {
//                    Log.d(tagCinema, "Success DB upload 1");
//                    Toast.makeText(InsideBody.this, "Upload done 1", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Log.d(tagCinema, "Failed DB upload 1");
//                    Toast.makeText(InsideBody.this, "Upload failed 1", Toast.LENGTH_SHORT).show();
//
//                });
//    }

//    public void download(View view) {
//        riversRef = mStorageRef.child("DBs/cinema.db");
//        riversRef.getDownloadUrl().addOnSuccessListener(uri -> {
//            downloadFile(InsideBody.this, "cinema", ".db", "Download/Cinema_temp", uri.toString());
//            Log.d(tagCinema, "download file success");
//        }).addOnFailureListener(e -> Log.d(tagCinema, "Error downloading DB from firebase onStop() method"));
//    }
//
//    private void downloadFile(Context context, String filename, String fileExtension, String destinationDirectory, String url) {
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, destinationDirectory, filename + fileExtension);
//
//        downloadManager.enqueue(request);
//    }


    //    public void showMessage(String title, String message){
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//    }

//    public void temp_method(View view) {
//        Cursor res=mDb.rawQuery("SELECT * FROM users", null);
//        StringBuilder sb=new StringBuilder();
//        while (res.moveToNext()){
//            sb.append("Id: ").append(res.getString(0)).append("\n");
//            sb.append("Name: ").append(res.getString(1)).append("\n");
//        }
//        showMessage("cinema.db", sb.toString());
//        res.close();
//    }
}