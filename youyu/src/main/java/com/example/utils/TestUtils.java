package com.example.utils;

import android.util.Log;

import com.example.youyu.api.SdkApi;

public class TestUtils {
    public static void showTest() {
        int resultNumber = SdkApi.TestSdkApi(1, 2);
        Log.d("youyu", "this is test model resultNumber:" + resultNumber);
    }
}
