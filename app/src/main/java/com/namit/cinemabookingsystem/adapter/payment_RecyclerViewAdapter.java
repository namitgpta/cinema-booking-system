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
import com.namit.cinemabookingsystem.after_payment;

public class payment_RecyclerViewAdapter extends RecyclerView.Adapter<payment_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    //private List<Cursor> screening_row_details;
    private Cursor c1;

    public payment_RecyclerViewAdapter(Context context, Cursor c1) {
        this.context = context;
        this.c1 =c1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        c1.moveToPosition(position);

        viewHolder.payment_name.setText(c1.getString(1));
//        String audi="Audi "+c1.getString(1);
//        viewHolder.room_no.setText(audi);
//        String start="Starts at "+c1.getString(2)+":00";
//        viewHolder.start_time.setText(start);
//        String init_price="Base Price: Rs."+c1.getString(4);
//        viewHolder.base_price.setText(init_price);
//        String portrait_photo=c1.getString(0)+"_portrait";
        int resId= context.getResources().getIdentifier(c1.getString(0), "raw", context.getPackageName());
        viewHolder.payment_portrait_photo.setBackgroundResource(resId);
    }

    @Override
    public int getItemCount() {
        return c1.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView payment_name;
//        public TextView room_no;
//        public TextView start_time;
//        public TextView base_price;
        public ImageView payment_portrait_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            payment_name=itemView.findViewById(R.id.method_of_payment);
//            room_no=itemView.findViewById(R.id.room_no);
//            start_time=itemView.findViewById(R.id.start_time);
//            base_price=itemView.findViewById(R.id.base_price);
            payment_portrait_photo=itemView.findViewById(R.id.paymentMethod_photo_portrait);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
//            Cursor c_temp= DatabaseHelper.myDataBase.rawQuery("select screenings._id from screenings " +
//                    "join films f on f._id = screenings.film_id " +
//                    "where film_id=?", new String[]{String.valueOf(InsideBody.current_counter)});
//            c_temp.moveToPosition(position);
//            int screenings_id=Integer.parseInt(c_temp.getString(0));
            Intent intent=new Intent(context, after_payment.class);
            intent.putExtra("position", String.valueOf(position));
//            c_temp.close();
            context.startActivity(intent);
        }
    }
}
