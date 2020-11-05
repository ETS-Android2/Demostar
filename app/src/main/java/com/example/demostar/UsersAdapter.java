package com.example.demostar;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterVH> implements Filterable {

    private List<UserModel> getUserModelListFiltered;
    private List<UserModel> userModelList;
    private Context context;
    private SelectedUser selectedUser;

    public UsersAdapter(List<UserModel> userModelList,SelectedUser selectedUser) {
        this.userModelList = userModelList;
        this.getUserModelListFiltered = userModelList;
        this.selectedUser = selectedUser;
    }

    @NonNull
    @Override
    public UsersAdapter.UsersAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_values,null));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UsersAdapterVH holder, int position) {
        UserModel userModel = userModelList.get(position);
        String username = userModel.getUserName();

//        MovieImage movieImage = data.get(position);
//        holder.imgMovie.setImageBitmap(DataConverter.convertByteArrayToImage(movieImage.getMovieImage()));
//        holder.tvUsername.setText(movieImage.getMovieName());

        // holder matching values according to values passed to app
        holder.tvUsername.setText(username);
        holder.imgMovie.setImageResource(userModel.getImageMovie());
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0)
                {
                    filterResults.count = getUserModelListFiltered.size();
                    filterResults.values = getUserModelListFiltered;
                }
                else{
                    String searchChar =  charSequence.toString().toLowerCase();

                    List<UserModel> resultData = new ArrayList<>();
                    for(UserModel userModel : getUserModelListFiltered)
                    {
                        if(userModel.getUserName().toLowerCase().contains(searchChar))
                        {
                            resultData.add(userModel);
                        }
                    }

                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userModelList = (List<UserModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public  interface SelectedUser{
        void selectedUser(UserModel userModel);
    }

    public class UsersAdapterVH  extends  RecyclerView.ViewHolder{
        TextView  tvUsername;
        ImageView imgIcon , imgMovie;

        public UsersAdapterVH(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.movieImage);
            tvUsername = itemView.findViewById(R.id.suffix);
            imgIcon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(userModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
