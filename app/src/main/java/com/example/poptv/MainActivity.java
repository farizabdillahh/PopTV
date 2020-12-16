package com.example.poptv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.poptv.adapter.TvAdapter;
import com.example.poptv.alarm.AlarmActivity;
import com.example.poptv.model.ResultsItem;
import com.example.poptv.model.TvModel;
import com.example.poptv.rest.ApiConfig;
import com.example.poptv.rest.ApiService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<ResultsItem> resultsItems;
    private TvAdapter tvAdapter;
    private Text mAlarm;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.poptv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resultsItems = new ArrayList<>();
        getData();
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
    }

    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getData()
                .enqueue(new Callback<TvModel>() {
                    @Override
                    public void onResponse(Call<TvModel> call, Response<TvModel> response) {
                        if (response.isSuccessful()){
                            resultsItems = response.body().getResults();
                            tvAdapter = new TvAdapter(resultsItems, getApplicationContext());
                            tvAdapter.notifyDataSetChanged();
                            rv.setAdapter(tvAdapter);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<TvModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rv = findViewById(R.id.recyclerView);
        mAlarm = findViewById(R.id.action_alarm);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_alarm:
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                String mOrderMessage = null;
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.apply();
    }
}