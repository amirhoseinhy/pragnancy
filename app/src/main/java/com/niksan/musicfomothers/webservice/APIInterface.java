package com.niksan.musicfomothers.webservice;

import com.niksan.musicfomothers.model.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("music.json")
   //@GET("music")
    Call<TrackResponse> getTracks();
}
