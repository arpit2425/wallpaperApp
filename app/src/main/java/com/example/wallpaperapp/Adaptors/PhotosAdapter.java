package com.example.wallpaperapp.Adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.models.Photo;
import com.example.wallpaperapp.utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private final String TAG = PhotosAdapter.class.getSimpleName();
    private List<Photo> photos;
    private Context context;

    public PhotosAdapter(Context context, List<Photo> photos){
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_photo, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Photo photo = photos.get(i);
        viewHolder.username.setText(photo.getUser().getUsername());
        Glide
                .with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600, 600)
                .into(viewHolder.photo);

        Glide
                .with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(viewHolder.userAvatar);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_photo_user)
        CircleImageView userAvatar;
        @BindView(R.id.item_photo_username)
        TextView username;
        @BindView(R.id.item_photo_photo)
        SquareImage photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
