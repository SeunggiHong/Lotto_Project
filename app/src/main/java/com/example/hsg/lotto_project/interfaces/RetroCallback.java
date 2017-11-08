package com.example.hsg.lotto_project.interfaces;

/**
 * Created by hsg on 2017. 11. 7..
 */


public interface RetroCallback<T> {
    void onError(Throwable t);

    void onSuccess(int code, T receivedData);

    void onFailure(int code);
}
