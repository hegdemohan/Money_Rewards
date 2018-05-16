package com.cipherscriptdevs.moneyrewards;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cipherscriptdevs.Utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class CheckInActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private String app_id;
    private String ad_unit_id;
    private String message = "";
    private Utils utils;
    private Button watch_video_btn;
    private TextView watch_video_suggestion;
    private RewardedVideoAd mRewardedVideoAd;
    private TextView check_in_info;
    private List<String> channel;
    private boolean rewarded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        utils = new Utils(this);

        check_in_info = findViewById(R.id.check_in_info);
        check_in_info.setText(R.string.check_in_desc);

        watch_video_btn = findViewById(R.id.watch_video_btn_check_in);
        watch_video_btn.setEnabled(false);
        watch_video_btn.setText(R.string.watch_video);
        watch_video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd();
            }
        });

        channel = Arrays.asList(getResources().getStringArray(R.array.channel_one_ids));
        app_id = channel.get(0);
        ad_unit_id = channel.get(1);

        watch_video_suggestion = findViewById(R.id.watch_video_suggestion);
        watch_video_suggestion.setText(R.string.loading_video);

        loadAd();
    }

    public void loadAd(){
        watch_video_btn.setEnabled(false);
        MobileAds.initialize(this,app_id);
        rewarded = false;
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd(ad_unit_id,new AdRequest.Builder().build());
    }

    public void showAd(){
        mRewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        watch_video_btn.setEnabled(true);
        watch_video_suggestion.setText(R.string.video_loaded);
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        if(!rewarded){
            Snackbar snackbar = Snackbar.make(this.getWindow().getDecorView().findViewById(android.R.id.content), R.string.not_completed_vid, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        loadAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        loadAd();
        watch_video_btn.setText(R.string.check_in_already_claimed);
        rewarded = true;
        watch_video_btn.setText(R.string.one_more_vid);
        watch_video_suggestion.setText(R.string.loading_video);
        message = getResources().getString(R.string.check_in_success);
        utils.openDialog(message);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        watch_video_suggestion.setText(R.string.not_available);
        watch_video_btn.setEnabled(false);
        showAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
