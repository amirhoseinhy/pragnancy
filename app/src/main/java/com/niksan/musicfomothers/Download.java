package com.niksan.musicfomothers;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.niksan.musicfomothers.model.Track;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Download extends IntentService {

    List<Track> tracks = new ArrayList<>();

    public Download() {
        super("Download");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


            ResultReceiver resultReceiver = intent.getParcelableExtra("downloadReciver");

            String urlownload = intent.getStringExtra("downloadUrl");
            String fileName = intent.getStringExtra("ID");


            int count;
            try {
                URL url = new URL(urlownload);
                URLConnection conexion = url.openConnection();
                conexion.connect();
                int lenghtOfFile = conexion.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(getApplicationContext().getExternalCacheDir() + "/" + fileName);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }

            Bundle bundle = new Bundle();
            bundle.putString("download", fileName);
            resultReceiver.send(1000, bundle);
    }

}

