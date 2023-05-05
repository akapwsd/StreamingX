package com.code.myapplication

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.code.listener.HttpRequestListener
import com.code.rtc.BaseRtcEngineManager
import com.code.youyu.api.Constants
import com.code.youyu.api.RtcManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : Activity() {
    companion object {
        const val MY_PERMISSION_REQUEST_CODE = 10000
    }

    private val permissionList = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE
    )
    private val channelId = "2824071274483947161"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        create_btn.setOnClickListener {
            val callType = Constants.VIDEO
            RtcManager.getInstance().createChannel("110", callType, object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val intent = Intent(this@MainActivity, AgoVideoActivity::class.java)
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
                    val intent = Intent(this@MainActivity, AgoVideoActivity::class.java)
                    intent.putExtra("localUid", 111)
                    intent.putExtra("peerUid", 110)
                    startActivity(intent);
                }

                override fun requestError(code: Int, error: String?) {
                    Toast.makeText(this@MainActivity,error,Toast.LENGTH_LONG).show()
                }
            })
        }
        call_audio.setOnClickListener {
            RtcManager.getInstance().callAudio(channelId, "111", "110", object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val intent = Intent(this@MainActivity, AgoAudioActivity::class.java)
                    intent.putExtra("localUid", 111)
                    intent.putExtra("peerUid", 110)
                    startActivity(intent);
                }

                override fun requestError(code: Int, error: String?) {
                    Toast.makeText(this@MainActivity,error,Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        val checkPermissionAllGranted = checkPermissionAllGranted(permissionList)
        if (checkPermissionAllGranted) {

        } else {
            ActivityCompat.requestPermissions(this, permissionList, MY_PERMISSION_REQUEST_CODE)
        }
    }

    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            var isAllGranted = true
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    break
                }
            }
            if (isAllGranted) {
                BaseRtcEngineManager.getInstance().initBaseRtc(this)
            } else {
                appExit(this)
            }
        }
    }

    private fun appExit(context: Context) {
        try {
            val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            activityManager.restartPackage(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}