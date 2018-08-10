package com.niksan.musicfomothers.adapter;

import android.view.View;

import pl.droidsonroids.gif.GifImageView;

public interface MyAdapterListener {
    void playOnClick(View v, int position);
    void pauseOnClick(View v, int position);
    void downloadOnClick(View v, int position);
}
