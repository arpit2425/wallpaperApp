package com.example.wallpaperapp.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.wallpaperapp.R;
import com.example.wallpaperapp.models.Photo;
import com.example.wallpaperapp.utils.SquareImage;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosAdatper extends RecyclerView.Adapter<PhotosAdatper.ViewHolder> {
    private final String TAG = PhotosAdatper.class.getSimpleName();
    private List<Photo> photos;
    private Context context;

    public PhotosAdatper(Context context, List<Photo> photos){
        this.photos = photos;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_photo_username)
        TextView username;
        @BindView(R.id.item_photo_photo)
        SquareImage photo;
        @BindView(R.id.item_photo_user)
        CircleImageView userAvatar;
        @BindView(R.id.item_photo_layout)
        FrameLayout frameLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_photo_layout)
        public void handleOnClick(){
            Log.d(TAG, "dmmmmmmmmm");
            int position = getAdapterPosition();
            String photoId = photos.get(position).getId();
            Intent intent = new Intent(context, FullscreenPhotoActivity.class);
            intent.putExtra("photoId", photoId);
            context.startActivity(intent);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.username.setText(photo.getUser().getUserName);
        GlideApp
                .with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600, 600)
                .into(holder.photo);

        GlideApp
                .with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(holder.userAvatar);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}