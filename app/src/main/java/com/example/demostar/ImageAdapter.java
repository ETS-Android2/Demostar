package com.example.demostar;

import android.content.Context;
import android.graphics.Movie;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MovieViewHolder> implements Filterable {

    private List<MovieImage> data, original;
    private SelectedUser selectedUser;

    public ImageAdapter(List<MovieImage> users) {
        data = users;
        original = users;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_values,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieImage movieImage = original.get(position);
        // holder matching values according to values passed to app
        holder.imgMovie.setImageBitmap(DataConverter.convertByteArrayToImage(movieImage.getMovieImage()));
        holder.tvUsername.setText(movieImage.getMovieName());
     }

    @Override
    public int getItemCount() {
        return original.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0)
                {
                    filterResults.count = data.size();
                    filterResults.values = data;
                }
                else{
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieImage> resultData = new ArrayList<>();
                    for(MovieImage movieImage : data)
                    {
                        if(movieImage.getMovieName().toLowerCase().contains(searchChar))
                            resultData.add(movieImage);
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                original = (List<MovieImage>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public  interface SelectedUser{
        void selectedUser(MovieImage movieImage);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView imgIcon , imgMovie;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.movieImageNew);
            tvUsername = itemView.findViewById(R.id.suffixNew);
            imgIcon = itemView.findViewById(R.id.imageViewNew);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    selectedUser.selectedUser(original.get(getAdapterPosition()));
//
//                }
//            });
        }
    }
}
