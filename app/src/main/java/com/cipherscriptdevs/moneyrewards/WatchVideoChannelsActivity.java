package com.cipherscriptdevs.moneyrewards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cipherscriptdevs.Utils.AppConstants;
import com.cipherscriptdevs.adapters.RecyclerViewAdapterWatchVideo;

import java.util.Arrays;
import java.util.List;

public class WatchVideoChannelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video_channels);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<String> watch_video_channels = Arrays.asList(getResources().getStringArray(R.array.watch_video_channels));
        AppConstants appConstants = new AppConstants();
        RecyclerViewAdapterWatchVideo adapter = new RecyclerViewAdapterWatchVideo(watch_video_channels,appConstants.watch_video_image,this);
        RecyclerView myView = findViewById(R.id.recyclerViewWatchVideo);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);
    }
}
