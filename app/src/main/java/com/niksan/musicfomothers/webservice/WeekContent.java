package com.niksan.musicfomothers.webservice;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.niksan.musicfomothers.R;

public class WeekContent extends AppCompatActivity {

    TextView txtContent;
    int recievedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_content);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        txtContent = findViewById(R.id.txtContent);
        recievedId = getIntent().getIntExtra("id", 0);

        Typeface face = Typeface.createFromAsset(this.getAssets(), "B Roya.ttf");
        txtContent.setTypeface(face);

        setWeekContent(recievedId);

    }

    private void setWeekContent(int id) {
        switch (id) {
            case 0:
                txtContent.setText(getString(R.string._1));
                break;
            case 1:
                txtContent.setText(getString(R.string._2));
                break;
            case 2:
                txtContent.setText(getString(R.string._3));
                break;
            case 3:
                txtContent.setText(getString(R.string._4));
                break;
            case 4:
                txtContent.setText(getString(R.string._5));
                break;
            case 5:
                txtContent.setText(getString(R.string._6));
                break;
            case 6:
                txtContent.setText(getString(R.string._7));
                break;
            case 7:
                txtContent.setText(getString(R.string._8));
                break;
            case 8:
                txtContent.setText(getString(R.string._9));
                break;
            case 9:
                txtContent.setText(getString(R.string._10));
                break;
            case 10:
                txtContent.setText(getString(R.string._11));
                break;
            case 11:
                txtContent.setText(getString(R.string._12));
                break;
            case 12:
                txtContent.setText(getString(R.string._13));
                break;
            case 13:
                txtContent.setText(getString(R.string._14));
                break;
            case 14:
                txtContent.setText(getString(R.string._15));
                break;
            case 15:
                txtContent.setText(getString(R.string._16));
                break;
            case 16:
                txtContent.setText(getString(R.string._17));
                break;
            case 17:
                txtContent.setText(getString(R.string._18));
                break;
            case 18:
                txtContent.setText(getString(R.string._19));
                break;
            case 19:
                txtContent.setText(getString(R.string._20));
                break;
            case 20:
                txtContent.setText(getString(R.string._21));
                break;
            case 21:
                txtContent.setText(getString(R.string._22));
                break;
            case 22:
                txtContent.setText(getString(R.string._23));
                break;
            case 23:
                txtContent.setText(getString(R.string._24));
                break;
            case 24:
                txtContent.setText(getString(R.string._25));
                break;
            case 25:
                txtContent.setText(getString(R.string._26));
                break;
            case 26:
                txtContent.setText(getString(R.string._27));
                break;
            case 27:
                txtContent.setText(getString(R.string._28));
                break;
            case 28:
                txtContent.setText(getString(R.string._29));
                break;
            case 29:
                txtContent.setText(getString(R.string._30));
                break;
            case 30:
                txtContent.setText(getString(R.string._31));
                break;
            case 31:
                txtContent.setText(getString(R.string._32));
                break;
            case 32:
                txtContent.setText(getString(R.string._33));
                break;
            case 33:
                txtContent.setText(getString(R.string._34));
                break;
            case 34:
                txtContent.setText(getString(R.string._35));
                break;
            case 35:
                txtContent.setText(getString(R.string._36));
                break;
            case 36:
                txtContent.setText(getString(R.string._37));
                break;
            case 37:
                txtContent.setText(getString(R.string._38));
                break;
            case 38:
                txtContent.setText(getString(R.string._39));
                break;
            case 39:
                txtContent.setText(getString(R.string._40));
                break;
            case 40:
                txtContent.setText(getString(R.string._41));
                break;



        }
    }

}
