package com.code.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.code.listener.HttpRequestListener
import com.code.youyu.api.Constants
import com.code.youyu.api.RtcManager
import kotlinx.android.synthetic.main.activity_login.*

class ChannelActivity : Activity() {
    private val channelId = "2824207831375289334"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        create_btn.setOnClickListener {
            val callType = Constants.VIDEO
            RtcManager.getInstance().createChannel("110", callType, object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val intent = Intent(this@ChannelActivity, AgoVideoActivity::class.java)
                    intent.putExtra("localUid", 110)
                    startActivity(intent)
                }

                override fun requestError(code: Int, error: String?) {

                }
            })
        }
        call_video.setOnClickListener {
            RtcManager.getInstance().callVideo(channelId, "111", "110", object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val intent = Intent(this@ChannelActivity, AgoVideoActivity::class.java)
                    intent.putExtra("localUid", 111)
                    intent.putExtra("peerUid", 110)
                    startActivity(intent);
                }

                override fun requestError(code: Int, error: String?) {
                    Toast.makeText(this@ChannelActivity, error, Toast.LENGTH_LONG).show()
                }
            })
        }
        call_audio.setOnClickListener {
            RtcManager.getInstance().callAudio(channelId, "111", "110", object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val intent = Intent(this@ChannelActivity, AgoAudioActivity::class.java)
                    intent.putExtra("localUid", 111)
                    intent.putExtra("peerUid", 110)
                    startActivity(intent);
                }

                override fun requestError(code: Int, error: String?) {
                    Toast.makeText(this@ChannelActivity, error, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}