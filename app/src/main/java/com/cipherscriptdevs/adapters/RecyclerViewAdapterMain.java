package com.cipherscriptdevs.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cipherscriptdevs.moneyrewards.CheckInActivity;
import com.cipherscriptdevs.moneyrewards.IronSourceOfferWallActivity;
import com.cipherscriptdevs.moneyrewards.PollFishSurveyActivity;
import com.cipherscriptdevs.moneyrewards.R;
import com.cipherscriptdevs.moneyrewards.WatchVideoChannelsActivity;

import java.util.List;

/**
 * Created by mhegde on 03/12/2018.
 */

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.MyViewHolder> {
    public List<String> description;
    public int[] imagePaths;
    public Context context;

    public RecyclerViewAdapterMain(List<String> description, int[] imagePaths , Context context){
        this.description = description;
        this.imagePaths = imagePaths;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvDescription.setText(description.get(position));
        holder.ivImage.setImageResource(imagePaths[position]);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(position);
            }
        });
    }

    private void redirect(int position) {
        switch (position){
            case 0:
                Intent intent_check_in = new Intent(context,CheckInActivity.class);
                context.startActivity(intent_check_in);
                break;
            case 1:
                Intent intent_survey = new Intent(context,PollFishSurveyActivity.class);
                context.startActivity(intent_survey);
                break;
            case 2:
                Intent intent_offerWall = new Intent(context,IronSourceOfferWallActivity.class);
                context.startActivity(intent_offerWall);
                break;
            case 3:
                Intent intent_watch_video = new Intent(context,WatchVideoChannelsActivity.class);
                context.startActivity(intent_watch_video);
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return description.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        private ImageView ivImage;
        private LinearLayout container;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.text_cardView);
            ivImage = itemView.findViewById(R.id.image_cardView);
            container = itemView.findViewById(R.id.container);
        }
    }
}