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
            RtcManager.getInstance().initRtc(this, "bfc26f2df48c11eda17ce69f99b16e0f", "nR753rmJ0PVgIw5UbceQdZHU!0lUtydywZOyVY5M", "8e625fec072ba9e09bf709f60fe7bfa126b7d2091bf839bf8b57d1c7d5fe81ec86")
            startActivity(Intent(this@MainActivity, ChannelActivity::class.java))
        }
        init_btn_2.setOnClickListener {
            RtcManager.getInstance().initRtc(this, "d55a2d32f48c11eda17ce69f99b16e0f", "#=g6wEkExBHAT0NRyecZaaJ=bTdJu!uhPiS9eqWn", "de8efe89aaf808a56ed86bad2a479bdd9bafda1c4c0cb700e7d2d67a50e91ae765")
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