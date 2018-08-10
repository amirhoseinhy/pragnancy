package com.niksan.musicfomothers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.niksan.musicfomothers.adapter.CalendarAdapter;
import com.niksan.musicfomothers.model.Week;

import java.util.ArrayList;
import java.util.List;


public class Calendar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Week> weeks = new ArrayList<>();
    private Week week ;
    private CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.week_recyclerview);
        adapter = new CalendarAdapter(weeks, this);

        addTitles();

        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        recyclerView.setAdapter(adapter);

    }

    private void addTitles(){

        for(int i=1; i<=41 ;i++) {
           //week = new Week(getString(stringsId[i]));
            week = new Week(String.valueOf(i));
           weeks.add(week);
        }
    }

}
