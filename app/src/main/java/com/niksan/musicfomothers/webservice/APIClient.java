package com.niksan.musicfomothers.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    private static final  String BASE_URL = "https://www.dl.dropboxusercontent.com/s/jyeje7cv2n10q4e/";


    public static Retrofit getClient(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }


}
