package com.example.poptv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.poptv.DetailActivity;
import com.example.poptv.R;
import com.example.poptv.model.ResultsItem;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private ArrayList<ResultsItem> mResultItem;
    private Context mContext;

    public TvAdapter(ArrayList<ResultsItem> mResulItem, Context mContext) {
        this.mResultItem = mResulItem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_judul.setText(mResultItem.get(position).getName());
        holder.tv_popularity.setText("Popularity: "+mResultItem.get(position).getPopularity().toString());
        String url = "https://image.tmdb.org/t/p/w200" + mResultItem.get(position).getPosterPath();
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(R.mipmap.logo).into(holder.iv_tv);
        holder.click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("satu", mResultItem.get(position).getOriginalName());
            intent.putExtra("dua", mResultItem.get(position).getFirstAirDate());
            intent.putExtra("tiga", mResultItem.get(position).getVoteAverage().toString());
            intent.putExtra("empat", mResultItem.get(position).getVoteCount().toString());
            intent.putExtra("lima", mResultItem.get(position).getOverview());
            intent.putExtra("enam", mResultItem.get(position).getBackdropPath());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mResultItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_tv;
        private TextView tv_judul, tv_popularity;
        private LinearLayout click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_tv = itemView.findViewById(R.id.iv_tv);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_popularity = itemView.findViewById(R.id.tv_popularity);
            click = itemView.findViewById(R.id.click);
        }
    }
}