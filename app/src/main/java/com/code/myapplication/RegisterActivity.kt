package com.code.myapplication

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.code.bean.SmsBean
import com.code.bean.TokenBean
import com.code.bean.UploadUserInfoBean
import com.code.listener.HttpRequestListener
import com.code.utils.HttpRequestUtils
import com.code.utils.LogUtil
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : Activity() {
    private var token = ""
    private var uid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var receipt = ""
        get_token.setOnClickListener {

        }
        apply_btn.setOnClickListener {
           
        }
        validate_code.setOnClickListener {

        }
    }
}