package com.cipherscriptdevs.moneyrewards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishSurveyReceivedListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.main.PollFish;

public class PollFishSurveyActivity extends AppCompatActivity implements
        com.pollfish.interfaces.PollfishSurveyCompletedListener, PollfishOpenedListener,
        PollfishClosedListener, PollfishSurveyReceivedListener,
        PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener {

    private Button coinsBtn;
    private TextView loggingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_fish_survey);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loggingTxt = findViewById(R.id.logText);
        coinsBtn = findViewById(R.id.coins_btn);

        coinsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (coinsBtn.getText() == getResources().getString(R.string.one_more_surveyy) || coinsBtn.getText() == getResources().getString(R.string.try_again)){
                    onResume();
                }else {
                    PollFish.show();
                }
            }
        });
        coinsBtn.setText(R.string.loading_survey);
        coinsBtn.setEnabled(false);
    }

    @Override
    public void onPollfishClosed() {
    }

    @Override
    public void onPollfishOpened() {

    }

    @Override
    public void onPollfishSurveyNotAvailable() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loggingTxt.setText(R.string.no_survey);
                coinsBtn.setText(R.string.try_again);
                coinsBtn.setEnabled(true);
            }
        });
    }

    @Override
    public void onUserNotEligible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loggingTxt.setText(R.string.not_eligible);
                coinsBtn.setText(R.string.no_surevy);
                coinsBtn.setEnabled(false);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        PollFish.ParamsBuilder paramsBuilder = new PollFish.ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558")
                .indicatorPadding(5)
                .customMode(true)
                .build();

        PollFish.initWith(this, paramsBuilder);
        PollFish.hide();

        loggingTxt.setText(R.string.check_survey);

        coinsBtn.setText(R.string.loading_survey);
        coinsBtn.setEnabled(false);
    }

    @Override
    public void onPollfishSurveyReceived(boolean playfulSurvey, int surveyPrice) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                coinsBtn.setText(R.string.take_the_survey);
                coinsBtn.setEnabled(true);
                loggingTxt.setText(R.string.survey_recieved);
            }
        });
    }

    @Override
    public void onPollfishSurveyCompleted(boolean playfulSurvey, int surveyPrice) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                coinsBtn.setText(R.string.one_more_surveyy);
                coinsBtn.setEnabled(true);
                loggingTxt.setText(R.string.survey_completed);
            }
        });
    }
}
