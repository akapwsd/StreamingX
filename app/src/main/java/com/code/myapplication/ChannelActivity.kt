package com.code.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.code.youyu.api.Constants
import kotlinx.android.synthetic.main.activity_login.*

class ChannelActivity : Activity() {
    private val channelId = "2825806909305530098"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        create_btn.setOnClickListener {
            val callType = Constants.VIDEO
        }
        call_video.setOnClickListener {

        }
        call_audio.setOnClickListener {

        }
        model_list.setOnClickListener {
            startActivity(Intent(this, ModelListActivity::class.java))
        }
    }
}