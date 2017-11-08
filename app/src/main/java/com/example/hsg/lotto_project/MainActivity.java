package com.example.hsg.lotto_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.hsg.lotto_project.interfaces.LottoInfo;
import com.example.hsg.lotto_project.retrofit.WinList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity   {

    private String TAG = MainActivity.class.getSimpleName();

    private static String url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber";

    ArrayList <HashMap<String,String>> contactList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView Lotto_seq;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("로또어플");
        setContentView(R.layout.activity_main);

        Lotto_seq = (TextView) findViewById(R.id.lotto_seq);

        run();
        setupLotto();

    }

    public void run(){
        LottoInfo apiService;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LottoInfo.Base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(LottoInfo.class);
        Call<WinList> WinData = apiService.getWinData(779);

        WinData.enqueue(new Callback<WinList>() {
            @Override
            public void onResponse(Call<WinList> call, Response<WinList> response) {

                response.body().toString();

            }

            @Override
            public void onFailure(Call<WinList> call, Throwable t) {

            }
        });




    }




    private void setupLotto(){

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this, getMenu());
        mRecyclerView.setAdapter(mAdapter);

    }



    private List<ActivityName> getMenu(){

        List<ActivityName> lottoList = new ArrayList<>();

        lottoList.add(new ActivityName("  번호생성",null));
        lottoList.add(new ActivityName("  랜덤생성",RandomActivity.class));
        lottoList.add(new ActivityName("  지정생성",AppointActivity.class));
        lottoList.add(new ActivityName("  생성번호보기",NlistActivity.class));
        lottoList.add(new ActivityName("  당첨결과",null));
        lottoList.add(new ActivityName("  판매점찾기",StoreActivity.class));
        lottoList.add(new ActivityName("  역대당첨금",HistoryActivity.class));


        return  lottoList;

    }


}
