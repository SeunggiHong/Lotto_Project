package com.example.hsg.lotto_project;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RandomActivity extends AppCompatActivity {

    private MySQLiteOpenHelper helper;
    String dbName = "random_.db";
    int dbVersion = 1;
    private SQLiteDatabase db;
    String tag = "SQLite";

    private Button makeBtn,saveBtn;
    private LinearLayout layout_number;
    private TextView[] tv_numbers;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("번호 랜덤생성");
        setContentView(R.layout.activity_random_num);

        mRecyclerView = (RecyclerView) findViewById(R.id.random_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter



        helper = new MySQLiteOpenHelper(
                this,
                dbName,
                null,
                dbVersion);

        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(tag, "DB error");
            e.printStackTrace();
            finish();

        }

        layout_number = (LinearLayout) findViewById(R.id.lottolayout);

        //textview 베열 생성
        tv_numbers = new TextView[6];

        //배열의 길이만큼 루프 getChildAt <- index의 뷰를 받아옴
        for (int i = 0; i<tv_numbers.length; i++){
            tv_numbers[i] = (TextView) layout_number.getChildAt(i);
        }

        makeBtn = (Button) findViewById(R.id.makeBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNum();

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {

//            String sql = "create table random_list(" +
//                    "id seq primary key AUTOINCREMENT" +
//                    " int l1m int l2, int l3, int l4, int l6,date text NOT NULL );";
            @Override
            public void onClick(View v) {

           String sql = "insert into random_list " + "values(null,'" +tv_numbers[0].getText().toString()
                                                                    +"','"+tv_numbers[1].getText().toString()
                                                                    +"','"+tv_numbers[2].getText().toString()
                                                                    +"','"+tv_numbers[3].getText().toString()
                                                                    +"','"+tv_numbers[4].getText().toString()
                                                                    +"','"+tv_numbers[5].getText().toString()
                                                                    +"');";

                Log.e(tag,""+sql);

            db.execSQL(sql);


            }
        });

        //셀렉트
        SelectNum();

    }

    private void SelectNum(){
        String sql = "select * from random_list;";

        Cursor result = db.rawQuery(sql,null);
        result.moveToFirst();
        while(!result.isAfterLast()){
            int id = result.getInt(0);
            String voca = result.getString(1);

            Toast.makeText(this,"seq= "+id+  " 1: " +result.getString(1) +" 2: " +result.getString(2) +" 3: " +result.getString(3) +" 4: " +result.getString(4), Toast.LENGTH_LONG).show();
            result.moveToNext();

        }
        result.close();

    }


    public void RandomNum(){

        int lottoNums[] = new int[6];
        int temp = 0;

        for(int i=0; i<lottoNums.length; i++) {
            lottoNums[i] = (int) (Math.random() * 45) + 1;
            //중복제거
            for(int j=i-1;j>=0;j--){
                if(lottoNums[i]==lottoNums[j]){
                    i--;
                    break;
                }
            }
        }
        //오름차순으로 sort
        for(int i=0;i<lottoNums.length;i++) {
            for (int j = 0; j < lottoNums.length; j++) {
                if (lottoNums[i] < lottoNums[j]) {
                    temp = lottoNums[i];
                    lottoNums[i] = lottoNums[j];
                    lottoNums[j] = temp;
                }
            }
        }
        for(int i=0; i<lottoNums.length; i++){
            tv_numbers[i].setText(""+lottoNums[i]);
        }
    }



}