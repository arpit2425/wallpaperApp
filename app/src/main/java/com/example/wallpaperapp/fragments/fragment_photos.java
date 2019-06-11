package com.example.wallpaperapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.wallpaperapp.R;
import com.example.wallpaperapp.models.Photo;
import com.example.wallpaperapp.webservices.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import com.example.wallpaperapp.webservices.servicegenerator;
import butterknife.BindView;
import butterknife.Unbinder;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class fragment_photos extends Fragment {
    private final String TAG=fragment_photos.class.getSimpleName();
    @BindView(R.id.picsRV)
    RecyclerView recyclerView;
    @BindView(R.id.picsProg)
    ProgressBar progressBar;
    private PhotosAdatper photosAdatper;
    private List<Photo> photos = new ArrayList<>();
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_phots,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photosAdatper = new PhotosAdatper(getActivity(), photos);
        recyclerView.setAdapter(photosAdatper);
        showProgressBar(true);
        getPhotos();
        return view;
    }
    private void getPhotos(){
        ApiInterface apiInterface =servicegenerator.createService(ApiInterface.class);
        Call<List<Photo>> call = apiInterface.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    for(Photo photo: response.body()){
                        photos.add(photo);
                        Log.d(TAG, photo.getUrl().getFull());
                    }
                    photosAdatper.notifyDataSetChanged();

                }else{
                    Log.d(TAG, "Fail" + response.message());
                }
                showProgressBar(false);
            }
            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(TAG, "Fail: " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
