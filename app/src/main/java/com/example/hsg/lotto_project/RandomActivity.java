package com.example.hsg.lotto_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RandomActivity extends AppCompatActivity {

    private Button makeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("번호 랜덤생성");
        setContentView(R.layout.activity_random_num);

        makeBtn = (Button) findViewById(R.id.makeBtn);

        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNum();
                
            }
        });

    }


    public void RandomNum(){

        int lottoNums[] = new int[6];


        for(int i=0; i<lottoNums.length; i++) {

            lottoNums[i] = (int) (Math.random() * 45) + 1;

            for(int j=0; j<i; j++){

                if(lottoNums[i] ==lottoNums[j]) {

                    i--;

                    break;
                }
            }

        }

        for(int i=0; i<lottoNums.length; i++){

            Toast.makeText(getApplicationContext(),"-"+lottoNums[i],Toast.LENGTH_SHORT).show();


        }

    }





}
