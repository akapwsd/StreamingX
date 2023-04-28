package com.code.myapplication

import android.app.Activity
import android.os.Bundle
import com.code.listener.HttpRequestListener
import com.code.youyu.api.RtcManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        send_btn.setOnClickListener {
            val uid = "0"
            val callType = 1
            RtcManager.getInstance().createChannel(uid, callType, object : HttpRequestListener {
                override fun requestSuccess(o: String?, msg: String?) {

                }

                override fun requestError(code: Int, error: String?) {

                }
            })
        }
    }
}