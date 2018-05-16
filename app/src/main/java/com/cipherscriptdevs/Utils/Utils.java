package com.cipherscriptdevs.Utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cipherscriptdevs.moneyrewards.R;

/**
 * Created by mhegde on 03/15/2018.
 */

public class Utils {

    Activity _activity;

    public Utils(Activity activity){
        _activity = activity;
    }
    public void openDialog(String message){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(_activity);
        final View dialogBox = _activity.getLayoutInflater().inflate(R.layout.dialog_success, null);
        TextView success_message = dialogBox.findViewById(R.id.success_message);
        success_message.setText(message);

        dialogBuilder.setView(dialogBox);
        dialogBuilder.setView(dialogBox);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        Button okay = dialogBox.findViewById(R.id.okay);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
