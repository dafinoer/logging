package com.example.dafidzeko.logging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dafidzeko.logging.controller.RestManager;
import com.example.dafidzeko.logging.model.Bunga;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private RestManager restManager;
    private ArrayAdapter<String> stringArrayAdapter = null;
    private List<String> mListItem;
    // private String[] mData = {"abc", " abce", "abcd", "abcdeds", "abcfdfa", "abcffa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);

        getData();
        mListItem = new ArrayList<>();
        stringArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item_view,
                R.id.text_view,
                mListItem);


        mListView.setAdapter(stringArrayAdapter);

    }

    private void getData() {
        restManager = new RestManager();

        Call<List<Bunga>> bungaCall = restManager.getService().getDataBunga();
        bungaCall.enqueue(new Callback<List<Bunga>>() {
            @Override
            public void onResponse(Call<List<Bunga>> call, Response<List<Bunga>> response) {
                if (response.isSuccessful()) {
                    List<Bunga> mBungaList = response.body();

                    for (int a = 0; a < mBungaList.size(); a++) {
                        Bunga bunga = mBungaList.get(a);
                        String abc = bunga.getName();
                        mListItem.add(abc);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<Bunga>> call, Throwable t) {
                Log.e("ERROR", String.valueOf(t));
            }
        });
    }
}
