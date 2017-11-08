package com.example.hsg.lotto_project.interfaces;

import com.example.hsg.lotto_project.retrofit.WinList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by hsg on 2017. 11. 3..
 */

public interface LottoInfo {

    //로또 당첨 정보를 얻는다.
    //http://www.nlotto.co.kr/common.do?method=getLottoNumber

    public static final String Base_URL = "http://www.nlotto.co.kr/";

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("common.do?method=getLottoNumber")
    Call<WinList> getWinData (
            @Field("drwNo") int round
    );

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("common.do?method=getLottoNumber")
    Call<WinList> getLatestWinData ( );





}
