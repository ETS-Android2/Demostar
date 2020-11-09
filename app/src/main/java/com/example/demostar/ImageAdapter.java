package com.example.demostar;

import android.content.Context;
import android.graphics.Movie;
import android.media.Image;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MovieViewHolder> implements Filterable {

    private List<MovieImage> data, original;
    private SelectedUser selectedUser;
    private Context context;

    public ImageAdapter(List<MovieImage> users,SelectedUser selectedUser) {
        data = users;
        original = users;
        this.selectedUser = selectedUser;
    }


    public  interface SelectedUser{
        void selectedUser(MovieImage movieImage);
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
        holder.tvMoviename.setText(movieImage.getMovieName());
        holder.tvMovieRating.setText(movieImage.getMovieRating());
        holder.tvMovieLang.setText(movieImage.getMovieLanguage());
        holder.tvMovieGenre.setText(movieImage.getMovieGenre());

     }

    @Override
    public int getItemCount() {
        return original.size();
    }


    @Override
    public Filter getFilter() {
        String [] languages = {"English", "Tamil", "French", "Japanese"};
        String [] genre = {"Action", "Education", "Motivation", "Horror", "Adventure", "Thriller", "Sports", "Comedy", "Science Fiction"};


        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null | charSequence.length() == 0)
                {

                    filterResults.count = data.size();
                    filterResults.values = data;
                }
                else if(Arrays.asList(languages).contains(charSequence)){
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieImage> resultData = new ArrayList<>();
                    for(MovieImage movieImage : data)
                    {
                        if(movieImage.getMovieLanguage().toLowerCase().contains(searchChar)) {
                            resultData.add(movieImage);
                        }
                    }

                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                else if(Arrays.asList(genre).contains(charSequence))
                {
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieImage> resultData = new ArrayList<>();
                    for(MovieImage movieImage : data)
                    {
                        if(movieImage.getMovieGenre().toLowerCase().contains(searchChar)) {
                            resultData.add(movieImage);
                        }
                    }

                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
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



    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvMoviename, tvMovieGenre , tvMovieLang, tvMovieRating;
        ImageView imgMovie;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.movieImageNew);
            tvMoviename = itemView.findViewById(R.id.suffixNew);
            tvMovieGenre = itemView.findViewById(R.id.tvGenre);
            tvMovieLang = itemView.findViewById(R.id.tvLanguage);
            tvMovieRating = itemView.findViewById(R.id.tvRating);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(original.get(getAdapterPosition()));
                }
            });
        }
    }
}
