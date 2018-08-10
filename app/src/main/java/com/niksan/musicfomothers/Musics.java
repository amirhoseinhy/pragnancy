package com.niksan.musicfomothers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.niksan.musicfomothers.adapter.Adapter;
import com.niksan.musicfomothers.adapter.MyAdapterListener;
import com.niksan.musicfomothers.model.Track;
import com.niksan.musicfomothers.model.TrackResponse;
import com.niksan.musicfomothers.webservice.APIClient;
import com.niksan.musicfomothers.webservice.APIInterface;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Musics extends AppCompatActivity {

    private ImageView trackImage, imgLogo;
    private RecyclerView recyclerView;
    private static Adapter adapter;
    private LinearLayoutManager layoutManager;
    private static List<Track> tracks;
    private Handler handler = new Handler();
    private static int currentItem;
    private ImageView imgDc;
   // private SwipeRefreshLayout refreshLayout;

    public static int[] fileSizes= {5652352,5492608,6180736,
            7624576, 4964224, 6260608,
            5748608, 6164352, 6723456,
            5779328, 5140352, 6131584,
            5156736, 5066624, 6596480,
            4083584, 4579200, 3604352,
            4083584, 5027712, 4915072,
            6274944, 4276096, 3555200,6354816};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        recyclerView = findViewById(R.id.recyclerview);
        trackImage = findViewById(R.id.image);
//        refreshLayout = findViewById(R.id.swipe);

        isOnline();
        request();


    /*    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isOnline()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    request();
                    refreshLayout.setRefreshing(false);
                } else refreshLayout.setRefreshing(false);
            }
        });*/


    }



    //وقتی خواندن تمام شد آپدیت کند ظاهر را
    public static class FinishPlaying extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.itemPlayState.put(currentItem, false);
            currentItem = -1;
            adapter.notifyDataSetChanged();
        }

    }

    void request() {
        final APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<TrackResponse> call = request.getTracks();
        call.enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(Call<TrackResponse> call, Response<TrackResponse> response) {
                if (response.isSuccessful()) {
                    tracks = response.body().getTracks();
                    isFileExist();
                    setRecyclerView(tracks);
                }
            }

            @Override
            public void onFailure(Call<TrackResponse> call, Throwable t) {
                Log.i("TAG", "Failed");
            }
        });
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        imgDc = findViewById(R.id.imgDc);
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            imgDc.setVisibility(View.GONE);
            return true;
        } else {

            Toast.makeText(this, "لطفا اتصال به اینترنت را بررسی کنید.", Toast.LENGTH_LONG).show();
            recyclerView.setVisibility(View.GONE);
            imgDc.setVisibility(View.VISIBLE);
            imgDc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isOnline()) {
                        request();
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
            return false;
        }
    }

    private void setRecyclerView(final List<Track> tracks) {
        adapter = new Adapter(tracks, this, new MyAdapterListener() {


            @Override
            public void playOnClick(View v, int position) {
                Intent intent1 = new Intent(getApplicationContext(), PlayerService.class);
                stopService(intent1);
                currentItem = position;

                Track.playingItem = position;

            /*    Glide.with(Musics.this)
                        .load(tracks.get(position)
                                .getImgurl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imgLogo);*/

                adapter.notifyItemChanged(position);
                Intent intent2 = new Intent(getApplicationContext(), PlayerService.class);
                intent2.putExtra("URL", tracks.get(position).getMusicurl());
                intent2.putExtra("ID", tracks.get(position).getId());
                startService(intent2);
            }


            @Override
            public void pauseOnClick(View v, int position/*, ImageButton btnPlay, ImageButton btnPause*/) {
                Intent intent = new Intent(getApplicationContext(), PlayerService.class);
                stopService(intent);

                adapter.itemPlayState.put(currentItem, false);
                adapter.notifyItemChanged(position);
                PlayerService.killMediaPlayer();
            }


            @Override
            public void downloadOnClick(View v, int position) {
                Toast.makeText(Musics.this, "در حال دانلود فایل ...", Toast.LENGTH_LONG).show();
                currentItem = position;
                adapter.notifyItemChanged(position);
                MyResultReciever resultReciever = new MyResultReciever(null);
                Intent intent = new Intent(getApplicationContext(), Download.class);
                intent.putExtra("downloadUrl", tracks.get(position).getMusicurl());
                intent.putExtra("ID", tracks.get(position).getId());
                intent.putExtra("downloadReciver", resultReciever);
                startService(intent);
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        if(Track.playingItem >0){
            adapter.itemPlayState.put(Track.playingItem, true);
            adapter.notifyItemChanged(Track.playingItem);
/*
            Glide.with(getApplicationContext())
                    .load(tracks.get(Track.playingItem)
                            .getImgurl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(trackImage);*/

        }
    }


    void isFileExist() {

        for (int i = 0; i < tracks.size(); i++) {
            File file = new File(getApplicationContext().getExternalCacheDir() + "/", tracks.get(i).getId());
            if (file.exists() && file.length() == fileSizes[i]) {
                adapter.itemFileState.put(i, true);
            } else adapter.itemFileState.put(i, false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Track.referedToActivity = true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // وقتی دانلود تمام شد
    private class MyResultReciever extends ResultReceiver {
        public MyResultReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(final int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == 1000 && resultData != null) {

                final int i = Integer.parseInt(resultData.getString("download"));
                adapter.itemDownloadState.put(i, false);
                File file = new File(getApplicationContext().getExternalCacheDir() + "/", tracks.get(i).getId());

                if (file.exists() && file.length() == fileSizes[i]) {
                    adapter.itemFileState.put(i, true);
                } else adapter.itemFileState.put(i, false);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemChanged(i);
                      /*  Intent intent = new Intent(getApplicationContext(), PlayerService.class);
                        intent.putExtra("URL", tracks.get(currentItem).getMusicurl());
                        intent.putExtra("ID", tracks.get(currentItem).getId());
                        startService(intent);*/
                    }
                });
            }

        }
    }





}
