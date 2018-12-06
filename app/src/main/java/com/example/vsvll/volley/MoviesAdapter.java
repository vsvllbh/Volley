package com.example.vsvll.volley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private ArrayList<pojoClass> pojoClassArrayList;
   private Context context;

    public MoviesAdapter(Context context,ArrayList<pojoClass> moviesList) {
        this.pojoClassArrayList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, Address1, City;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            Address1 = (TextView) view.findViewById(R.id.Address1);
            City = (TextView) view.findViewById(R.id.City);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        pojoClass  movie = pojoClassArrayList.get(position);
        holder.title.setText(movie.getName());
        holder.Address1.setText(movie.getAddress1());
        holder.City.setText(movie.getCity());
    }

    @Override
    public int getItemCount() {
        return pojoClassArrayList.size();
    }
}