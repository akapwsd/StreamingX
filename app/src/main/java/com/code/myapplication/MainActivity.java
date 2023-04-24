package com.code.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.code.bean.ModelBean;
import com.code.listener.RtcRequestEventHandler;
import com.code.utils.LogUtil;
import com.code.utils.ThreadPoolUtils;
import com.code.youyu.api.RtcManager;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    private final HashMap<Integer, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}