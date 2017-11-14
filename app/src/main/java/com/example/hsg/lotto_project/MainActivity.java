package com.example.hsg.lotto_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.hsg.lotto_project.interfaces.LottoInfo;
import com.example.hsg.lotto_project.retrofit.WinList;

import java.io.IOException;
import java.text.NumberFormat;
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
    private int Thousand;

    private static String url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber";

    ArrayList <HashMap<String,String>> contactList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView Lotto_seq, Lotto_num1, Lotto_num2, Lotto_num3, Lotto_num4, Lotto_num5, Lotto_num6, Lotto_winamt, Lotto_bonus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("로또어플");
        setContentView(R.layout.activity_main);

        Lotto_seq = (TextView) findViewById(R.id.lotto_seq);
        Lotto_num1 = (TextView) findViewById(R.id.lotto_num1);
        Lotto_num2 = (TextView) findViewById(R.id.lotto_num2);
        Lotto_num3 = (TextView) findViewById(R.id.lotto_num3);
        Lotto_num4 = (TextView) findViewById(R.id.lotto_num4);
        Lotto_num5 = (TextView) findViewById(R.id.lotto_num5);
        Lotto_num6 = (TextView) findViewById(R.id.lotto_num6);
        Lotto_winamt = (TextView) findViewById(R.id.lotto_winamt);
        Lotto_bonus = (TextView) findViewById(R.id.lotto_bonus);




        run();
        setupLotto();

    }

    public void run(){
        LottoInfo apiService;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LottoInfo.Base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(LottoInfo.class);

        Call<WinList> WinData = apiService.getLatestWinData();

        WinData.enqueue(new Callback<WinList>() {
            @Override
            public void onResponse(Call<WinList> call, Response<WinList> response) {

<<<<<<< HEAD
                try{


                    Lotto_seq.setText("제 "+response.body().getDrwNo() +'회');

                    Lotto_num1.setText(response.body().getDrwtNo1());
                    Lotto_num2.setText(response.body().getDrwtNo2());
                    Lotto_num3.setText(response.body().getDrwtNo3());
                    Lotto_num4.setText(response.body().getDrwtNo4());
                    Lotto_num5.setText(response.body().getDrwtNo5());
                    Lotto_num6.setText(response.body().getDrwtNo6());

                    Thousand = Integer.parseInt(response.body().getFirstWinamnt());
                    Lotto_winamt.setText("당첨금: " +
                            NumberFormat.getInstance().format(Integer.parseInt(response.body().getFirstWinamnt()))+"원");

                    Lotto_bonus.setText(response.body().getBnusNo());


                    Log.d("TAG", "response : "+ response.body().getDrwtNo1() + " / "+response.body().getDrwtNo2());

                }catch (Exception e){
                    e.printStackTrace();
                }



=======
                Log.d("TAG", "response : "+ response.body().getDrwtNo1() + " / "+response.body().getDrwtNo2());
>>>>>>> b456cd41118662977dc9a854edbbe330f12fc8b9

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
