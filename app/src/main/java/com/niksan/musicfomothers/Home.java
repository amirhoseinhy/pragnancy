package com.niksan.musicfomothers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationViewEx bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setIconSize(48, 48);
        bottomNavigation.enableAnimation(true);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();

    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted1");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted1");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted2");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted2");
            return true;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.music:
                startActivity(new Intent(Home.this, Musics.class));
                break;
            case R.id.calendar:
                startActivity(new Intent(Home.this, Calendar.class));
                break;
            case R.id.about:
                Intent intent1 =new Intent(Home.this, Text.class);
                startActivity(intent1);
                break;
            case R.id.share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "تقویم بارداری");
                    String sAux = "\nتقویم بارداری\n\n";
                    sAux = sAux + "https://myket.ir/app/com.niksan.musicfomothers";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, ""));
                } catch (Exception e) {
                    //e.toString();
                }
                break;
            case R.id.contact:
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:mramir2008@gmail.com"));
                email.putExtra(Intent.EXTRA_SUBJECT, "تقویم بارداری");
                startActivity(email);
                break;

        }
        return false;
    }

}
