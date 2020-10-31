package com.namit.cinemabookingsystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.namit.cinemabookingsystem.review_payment;
import com.namit.cinemabookingsystem.snacks;

public class snacks_RecyclerViewAdapter extends RecyclerView.Adapter<snacks_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    //private List<Cursor> screening_row_details;
    private Cursor c1;
    public static int snacks_booked=0;

    public snacks_RecyclerViewAdapter(Context context, Cursor c1) {
        this.context = context;
        this.c1 =c1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.snacks_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        c1.moveToPosition(position);

        viewHolder.snack_name.setText(c1.getString(1));
        int snackPrice=c1.getInt(2);
        String init_price="Price: Rs."+snackPrice;
        viewHolder.snack_price.setText(init_price);
        viewHolder.qty.setText(String.valueOf(0));
        viewHolder.plus.setOnClickListener(v -> {
            snacks_booked++;
            viewHolder.qty.setText(String.valueOf(Integer.parseInt(viewHolder.qty.getText().toString())+1));
            Seat_selection.amount+=snackPrice;
            String total_amount_snacks_str="Total Amount: Rs."+Seat_selection.amount;
            snacks.total_amount_snacks.setText(total_amount_snacks_str);
        });
        viewHolder.minus.setOnClickListener(v -> {
            if(Integer.parseInt(viewHolder.qty.getText().toString())>0){
                snacks_booked--;
                viewHolder.qty.setText(String.valueOf(Integer.parseInt(viewHolder.qty.getText().toString())-1));
                Seat_selection.amount-=snackPrice;
                String total_amount_snacks_str="Total Amount: Rs."+Seat_selection.amount;
                snacks.total_amount_snacks.setText(total_amount_snacks_str);
            }

        });
//        String portrait_photo=c1.getString(0)+"_portrait";
        int resId= context.getResources().getIdentifier(c1.getString(0), "raw", context.getPackageName());
        viewHolder.snack_portrait_photo.setBackgroundResource(resId);
    }

    @Override
    public int getItemCount() {
        return c1.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView snack_name;
        public Button plus, minus;
        public TextView qty;
        public TextView snack_price;
        public ImageView snack_portrait_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            snack_name=itemView.findViewById(R.id.snack_name);
            qty=itemView.findViewById(R.id.quantity_snack);
            plus=itemView.findViewById(R.id.plus_snack);
            minus=itemView.findViewById(R.id.minus_snack);
            snack_price=itemView.findViewById(R.id.price_snack);
            snack_portrait_photo=itemView.findViewById(R.id.snack_photo_portrait);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
//            Cursor c_temp= DatabaseHelper.myDataBase.rawQuery("select screenings._id from screenings " +
//                    "join films f on f._id = screenings.film_id " +
//                    "where film_id=?", new String[]{String.valueOf(InsideBody.current_counter)});
//            c_temp.moveToPosition(position);
//            int screenings_id=Integer.parseInt(c_temp.getString(0));


//            Intent intent=new Intent(context, review_payment.class);
//            intent.putExtra("position", String.valueOf(position));
////            c_temp.close();
//            context.startActivity(intent);
        }
    }
}
