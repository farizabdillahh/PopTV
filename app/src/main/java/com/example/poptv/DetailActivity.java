package com.example.poptv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView ori_name = findViewById(R.id.ori_name);
        TextView tgl_tayang = findViewById(R.id.tgl_tayang);
        TextView vote_avg = findViewById(R.id.vote_avg);
        TextView vote_count = findViewById(R.id.vote_count);
        TextView sinopsis = findViewById(R.id.sinopsis);
        ImageView iv_backdrop = findViewById(R.id.iv_backdrop);

        ori_name.setText(getIntent().getStringExtra("satu"));
        tgl_tayang.setText("Tayang Pertama: "+getIntent().getStringExtra("dua"));
        vote_avg.setText("Rating: "+getIntent().getStringExtra("tiga"));
        vote_count.setText("Voters: "+getIntent().getStringExtra("empat"));
        sinopsis.setText(getIntent().getStringExtra("lima"));
        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("enam")).into(iv_backdrop);
    }
}