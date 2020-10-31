package com.namit.cinemabookingsystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.namit.cinemabookingsystem.DatabaseHelper;
import com.namit.cinemabookingsystem.InsideBody;
import com.namit.cinemabookingsystem.R;
import com.namit.cinemabookingsystem.Seat_selection;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    //private List<Cursor> screening_row_details;
    private Cursor c1;

    public RecyclerViewAdapter(Context context, Cursor c1) {
        this.context = context;
        this.c1 =c1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.screening_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        c1.moveToPosition(position);

        viewHolder.movie_name.setText(c1.getString(0));
        String audi="Audi "+c1.getString(1);
        viewHolder.room_no.setText(audi);
        String start="Starts at "+c1.getString(2)+":00";
        viewHolder.start_time.setText(start);
        String init_price="Base Price: Rs."+c1.getString(4);
        viewHolder.base_price.setText(init_price);
        String portrait_photo=c1.getString(3)+"_portrait";
        //Toast.makeText(context, portrait_photo, Toast.LENGTH_SHORT).show();
        int resId= context.getResources().getIdentifier(portrait_photo, "raw", context.getPackageName());
        viewHolder.movie_portrait_photo.setBackgroundResource(resId);
    }

    @Override
    public int getItemCount() {
        return c1.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView movie_name;
        public TextView room_no;
        public TextView start_time;
        public TextView base_price;
        public ImageView movie_portrait_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            movie_name=itemView.findViewById(R.id.movie_name);
            room_no=itemView.findViewById(R.id.room_no);
            start_time=itemView.findViewById(R.id.start_time);
            base_price=itemView.findViewById(R.id.base_price);
            movie_portrait_photo=itemView.findViewById(R.id.movie_photo_portrait);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            c1.moveToPosition(position);
            //Toast.makeText(context, c1.getString(0), Toast.LENGTH_SHORT).show();

            DatabaseHelper.myDataBase.execSQL("update bookings set movie_name=?, room_name=?, timing=?, movie_name_str_id=?",
                    new String[]{c1.getString(0), c1.getString(1), c1.getString(2), c1.getString(3)});

            Cursor c_temp= DatabaseHelper.myDataBase.rawQuery("select screenings._id from screenings " +
                    "join films f on f._id = screenings.film_id " +
                            "where film_id=?", new String[]{String.valueOf(InsideBody.current_counter)});
            c_temp.moveToPosition(position);
            int screenings_id=Integer.parseInt(c_temp.getString(0));
            Intent intent=new Intent(context, Seat_selection.class);
            intent.putExtra("id", String.valueOf(screenings_id));
            c_temp.close();
            context.startActivity(intent);
        }
    }
}
