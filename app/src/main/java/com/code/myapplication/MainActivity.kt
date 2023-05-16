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
            RtcManager.getInstance().initRtc(this, "848792c0f3bf11eda17ce69f99b16e0f", "gA-FFC9W0@0KYz7kD5@NasodqTlL0e=JlqvGWWSW", "791d631f89cf81049b97f229a654887e1e90f88ead871d60e7e8d4014e8543deca")
            startActivity(Intent(this@MainActivity, ChannelActivity::class.java))
        }
        init_btn_2.setOnClickListener {
            RtcManager.getInstance().initRtc(this, "b7eede93f3b411eda17ce69f99b16e0f", "kNhV--+5-OD7+YTzQDpE1bU0PjS5KcX=Q9xrnZG1", "a14b97f80bba6a11571b104d4d31781efd717cbfba45fb4d147efba3ae80c4b35b")
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