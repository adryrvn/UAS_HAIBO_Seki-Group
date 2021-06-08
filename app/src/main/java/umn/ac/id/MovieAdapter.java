package umn.ac.id;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;

import umn.ac.id.MovieItem;
import umn.ac.id.FavDB;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieItem> movieItems;
    private Context context;
    private FavDB favDB;

    public MovieAdapter(ArrayList<MovieItem> movieItems, Context context) {
        this.movieItems = movieItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {

        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        final MovieItem movieItem = movieItems.get(position);


        holder.imageView1.setImageResource(movieItem.getImageResourse());
        holder.titleTextView.setText(movieItem.getTitle());
    }


    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView1;
        TextView titleTextView, likeCountTextView;
        Button favBtn;
        ConstraintLayout MovieList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView1 = itemView.findViewById(R.id.imageView1);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            itemView.setOnClickListener(this);


            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    MovieItem movieItem = movieItems.get(position);
                    likeClick(movieItem, favBtn, likeCountTextView);
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (getAdapterPosition()) {

                case 0:
                    Intent korea1intent = new Intent(context, Korea1Activity.class);
                    context.startActivity(korea1intent);
                    break;

                case 1:
                    Intent korea2intent = new Intent(context, Korea2Activity.class);
                    context.startActivity(korea2intent);
                    break;
                case 2:
                    Intent jepang1intent = new Intent(context, Jepang1Activity.class);
                    context.startActivity(jepang1intent);
                    break;
                case 3:
                    Intent jepang2intent = new Intent(context, Jepang2Activity.class);
                    context.startActivity(jepang2intent);
                    break;
            }
        }

        private void createTableOnFirstStart() {
            favDB.insertEmpty();

            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        }

        private void readCursorData(MovieItem movieItem, ViewHolder viewHolder) {
            Cursor cursor = favDB.read_all_data(movieItem.getKey_id());
            SQLiteDatabase db = favDB.getReadableDatabase();
            try {
                while (cursor.moveToNext()) {
                    String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                    movieItem.setFavStatus(item_fav_status);

                    //check fav status
                    if (item_fav_status != null && item_fav_status.equals("1")) {
                        viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    } else if (item_fav_status != null && item_fav_status.equals("0")) {
                        viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    }
                }
            } finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
            }

        }

        // like click
        private void likeClick(MovieItem coffeeItem, Button favBtn, final TextView textLike) {
            DatabaseReference refLike = FirebaseDatabase.getInstance().getReference().child("likes");
            final DatabaseReference upvotesRefLike = refLike.child(coffeeItem.getKey_id());

            if (coffeeItem.getFavStatus().equals("0")) {

                coffeeItem.setFavStatus("1");
                favDB.insertIntoTheDatabase(coffeeItem.getTitle(), coffeeItem.getImageResourse(),
                        coffeeItem.getKey_id(), coffeeItem.getFavStatus());
                favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                favBtn.setSelected(true);

                upvotesRefLike.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
                        try {
                            Integer currentValue = mutableData.getValue(Integer.class);
                            if (currentValue == null) {
                                mutableData.setValue(1);
                            } else {
                                mutableData.setValue(currentValue + 1);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        System.out.println("Transaction completed");
                    }
                });


            } else if (coffeeItem.getFavStatus().equals("1")) {
                coffeeItem.setFavStatus("0");
                favDB.remove_fav(coffeeItem.getKey_id());
                favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                favBtn.setSelected(false);

                upvotesRefLike.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
                        try {
                            Integer currentValue = mutableData.getValue(Integer.class);
                            if (currentValue == null) {
                                mutableData.setValue(1);
                            } else {
                                mutableData.setValue(currentValue - 1);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        System.out.println("Transaction completed");
                    }
                });
            }


        }
    }
}
