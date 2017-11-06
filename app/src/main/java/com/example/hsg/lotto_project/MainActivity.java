package com.example.hsg.lotto_project;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsg.lotto_project.com.example.lotto_project.ActivityName;
import com.example.hsg.lotto_project.com.example.lotto_project.HttpHandler;
import com.example.hsg.lotto_project.com.example.lotto_project.MyAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private String TAG = MainActivity.class.getSimpleName();

    private static String url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber";

    ArrayList <HashMap<String,String>> contactList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView Lotto_seq;

    public int drwNo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("로또어플");
        setContentView(R.layout.activity_main);
        Lotto_seq = (TextView) findViewById(R.id.lotto_seq);
        contactList = new ArrayList<>();

        new GetContacts().execute();

        Lotto_seq.setText(Integer.toString(drwNo));

        setupLotto();

    }

    public class GetContacts extends AsyncTask <Void, Void, Void>
    {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

//                  drwNo = jsonObject.getJSONObject("drwNo").toString();

                    drwNo = jsonObject.getInt("drwNo");
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }


            return null;
        }
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
