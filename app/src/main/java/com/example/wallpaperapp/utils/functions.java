package com.example.wallpaperapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.wallpaperapp.R;

public class functions {
    public static void change(FragmentActivity fragmentActivity, Fragment fragment)
    {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,fragment)
                .commit();
    }
}
