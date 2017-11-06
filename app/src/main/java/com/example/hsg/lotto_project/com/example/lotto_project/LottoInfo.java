package com.example.hsg.lotto_project.com.example.lotto_project;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hsg on 2017. 11. 3..
 */

public interface LottoInfo {

    //로또 당첨 정보를 얻는다.
    //http://www.nlotto.co.kr/common.do?method=getLottoNumber

    public static final String URL_PARS = "http://www.nlotto.co.kr/";
//    @Headers({"Content-Type:application/x-www-form-urlencoded"})
//    @FormUrlEncoded
//    @POST("common.d o?method=getLottoNumber");
    Call<WinList>





}
