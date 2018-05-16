package com.cipherscriptdevs.moneyrewards;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ironsource.adapters.supersonicads.SupersonicConfig;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.OfferwallListener;

/**
 * Created by mhegde on 04/05/2018.
 */

public class IronSourceOfferWallActivity extends AppCompatActivity implements OfferwallListener {

    private TextView loggingTxt;
    private final String APP_KEY = "4ea90fad";
    private final String FALLBACK_USER_ID = "userId";
    private Button offerwallButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_wall);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        IntegrationHelper.validateIntegration(this);
        initUIElements();
        startIronSourceInitTask();
    }

    private void startIronSourceInitTask(){

        // getting advertiser id should be done on a background thread
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return IronSource.getAdvertiserId(IronSourceOfferWallActivity.this);
            }
            @Override
            protected void onPostExecute(String advertisingId) {
                if (TextUtils.isEmpty(advertisingId)) {
                    advertisingId = FALLBACK_USER_ID;
                }
                // we're using an advertisingId as the 'userId'
                initIronSource(APP_KEY, advertisingId);
            }
        };
        task.execute();
    }

    public void handleOfferwallButtonState(final boolean available) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                offerwallButton.setEnabled(available);
                if (available){
                    loggingTxt.setText("Offerwall Loaded");
                }else {
                    loggingTxt.setText("Loading");
                }
            }
        });
    }

    private void initUIElements() {
        offerwallButton = findViewById(R.id.coins_btn);
        offerwallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IronSource.isOfferwallAvailable())
                    IronSource.showOfferwall();
            }
        });
        loggingTxt = findViewById(R.id.logText);
        loggingTxt.setText("Loading");

    }

    private void initIronSource(String appKey, String userId) {
        // set the IronSource offer wall listener
        IronSource.setOfferwallListener(this);
        // set client side callbacks for the offer wall
        SupersonicConfig.getConfigObj().setClientSideCallbacks(true);

        IronSource.setUserId(userId);
        // init the IronSource SDK
        IronSource.init(this, appKey);
        // In order to work with IronSourceBanners you need to add Providers who support banner ad unit and uncomment next line
    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
        handleOfferwallButtonState(IronSource.isOfferwallAvailable());
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    @Override
    public void onOfferwallAvailable(boolean available) {
        handleOfferwallButtonState(available);
    }

    @Override
    public void onOfferwallOpened() {

    }

    @Override
    public void onOfferwallShowFailed(IronSourceError ironSourceError) {

    }

    @Override
    public boolean onOfferwallAdCredited(int i, int i1, boolean b) {
        return false;
    }

    @Override
    public void onGetOfferwallCreditsFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onOfferwallClosed() {

    }
}
