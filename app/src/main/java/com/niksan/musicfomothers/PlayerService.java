package com.niksan.musicfomothers;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.io.IOException;


public class PlayerService extends Service {

    public static int playingItem;


    private Context context = this;

    public PlayerService() {
    }


    public static MediaPlayer mediaPlayer = new MediaPlayer();
    private static int pausePosition, currentMusic;
    private static String id, url;

    //هر بار که سرویس صدا زده شود این هم اجرا میشود
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            url = intent.getStringExtra("URL");
            id = intent.getStringExtra("ID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        playingItem = Integer.valueOf(id);

        killMediaPlayer();


        new MyAsyncTask(context).execute(url, id);
        // return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
      //  return START_REDELIVER_INTENT;
    }


    // در این نوع سرویس باید اورراید شود
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<String, Void, Void> {

        Context mContext;

        private MyAsyncTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... parametrs) {

            mediaPlayer = new MediaPlayer();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Intent intent = new Intent("finishPlaying");
                    mContext.sendBroadcast(intent);
                }
            });


            String path = getApplicationContext().getExternalCacheDir() + "/" + parametrs[1];
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //    }
            return null;


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           /* new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent("finishPlaying");
                    mContext.sendBroadcast(intent);
                }
            }, mediaPlayer.getDuration());*/
        }
    }


    public static void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

/*
    public static void pauseMusic() {
        pausePosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(pausePosition);
        mediaPlayer.pause();
        Track.setPlayStatus(false);
        currentMusic = Integer.valueOf(id);
    }

    public static void forward() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 15000);
        }
    }

    public static void backward() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 15000);
        }
    }

*/

}
