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
    private LinearLayout layout_number;
    private TextView[] tv_numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("번호 랜덤생성");
        setContentView(R.layout.activity_random_num);

        layout_number = (LinearLayout) findViewById(R.id.lottolayout);

        //textview 베열 생성
        tv_numbers = new TextView[6];

        //배열의 길이만큼 루프 getChildAt <- index의 뷰를 받아옴
        for (int i = 0; i<tv_numbers.length; i++){
            tv_numbers[i] = (TextView) layout_number.getChildAt(i);
        }

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
//            Toast.makeText(getApplicationContext(), ""+lottoNums[i],Toast.LENGTH_SHORT).show();


        }

    }





}