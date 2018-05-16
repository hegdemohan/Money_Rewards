package com.cipherscriptdevs.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cipherscriptdevs.moneyrewards.R;
import com.cipherscriptdevs.moneyrewards.WatchVideoActivity;
import com.cipherscriptdevs.moneyrewards.WatchVideoChannelsActivity;

import java.util.List;

/**
 * Created by mhegde on 03/14/2018.
 */

public class RecyclerViewAdapterWatchVideo extends RecyclerView.Adapter<RecyclerViewAdapterWatchVideo.MyViewHolder>{
    public int imagePath;
    public List<String> descriptions;
    public Context context;
    public RecyclerViewAdapterWatchVideo(List<String> descriptions, int imagePath , Context context){
        this.descriptions = descriptions;
        this.imagePath = imagePath;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvDescription.setText(descriptions.get(position));
        holder.ivImage.setImageResource(imagePath);
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
                Intent intent_watch_video1 = new Intent(context,WatchVideoActivity.class);
                intent_watch_video1.putExtra("channel",position);
                context.startActivity(intent_watch_video1);
                break;
            case 1:
                Intent intent_watch_video2 = new Intent(context,WatchVideoActivity.class);
                intent_watch_video2.putExtra("channel",position);
                context.startActivity(intent_watch_video2);
                break;
            case 2:
                Intent intent_watch_video3 = new Intent(context,WatchVideoActivity.class);
                intent_watch_video3.putExtra("channel",position);
                context.startActivity(intent_watch_video3);
                break;
            case 3:
                Intent intent_watch_video4 = new Intent(context,WatchVideoActivity.class);
                intent_watch_video4.putExtra("channel",position);
                context.startActivity(intent_watch_video4);
                break;
            case 4:
                Intent intent_watch_video5 = new Intent(context,WatchVideoActivity.class);
                intent_watch_video5.putExtra("channel",position);
                context.startActivity(intent_watch_video5);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
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
