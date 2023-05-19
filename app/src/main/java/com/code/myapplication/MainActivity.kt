package com.code.myapplication

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.code.rtc.BaseRtcEngineManager
import com.code.youyu.api.RtcManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : Activity() {
    companion object {
        const val MY_PERMISSION_REQUEST_CODE = 10000
    }

    private val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_btn_1.setOnClickListener {
            RtcManager.getInstance().initRtc(this, "9b24e9bcf60b11eda17ce69f99b16e0f", "xCV!!XyvNHHA0Ik@+sa91f9le=WXK-E7a3lVPmS8", "1bb470d43675862e6c71a1f99ddf1e47af3718c7cec0170a8a9a4ed1730a9a0c39")
            startActivity(Intent(this@MainActivity, ChannelActivity::class.java))
        }
        init_btn_2.setOnClickListener {
            RtcManager.getInstance().initRtc(this, "b4cf7180f60b11eda17ce69f99b16e0f", "EoM2#0XGjAWVGF-v0zOio6B66_cTB7YdMndPKFyp", "5cb39cc507a223bdbd2a293c03902332c5cfd287d7c77327b91b09e677b66fa427")
            startActivity(Intent(this@MainActivity, ChannelActivity::class.java))
        }
        resume_btn_1.setOnClickListener {

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