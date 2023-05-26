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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        LogUtil.d("ZHIZHI", "start")
        var receipt = ""
        get_token.setOnClickListener {
            HttpRequestUtils.getInstance().requestPhoneCode(this, "8617365657171", object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {
                    val smsBean = o as SmsBean
                    receipt = smsBean.receipt

                }

                override fun requestError(code: Int, error: String?) {

                }
            })
        }
        apply_btn.setOnClickListener {
            val uploadUserInfoBean = UploadUserInfoBean()
            uploadUserInfoBean.setNick("lala")
            uploadUserInfoBean.setBirthday("123456")
            HttpRequestUtils.getInstance().applyModel(this, uploadUserInfoBean, object : HttpRequestListener {
                override fun requestSuccess(o: Any?) {

                }

                override fun requestError(code: Int, error: String?) {

                }
            })
        }
        validate_code.setOnClickListener {
            if (!TextUtils.isEmpty(receipt)) {
                HttpRequestUtils.getInstance().validateSmsCode(this, receipt, "123456", object : HttpRequestListener {
                    override fun requestSuccess(o: Any?) {
                        val tokenBean = o as TokenBean
                        receipt_tv.text = "The token is ${tokenBean.token} and Uid is ${tokenBean.id}"
//                        HttpRequestUtils.getInstance().uploadNickname(this@RegisterActivity,"zhizhi",object :HttpRequestListener{
//                            override fun requestSuccess(o: Any?) {
//
//                            }
//
//                            override fun requestError(code: Int, error: String?) {
//
//                            }
//                        })
                    }

                    override fun requestError(code: Int, error: String?) {

                    }
                })
            } else {
                Toast.makeText(this, "null", Toast.LENGTH_LONG).show()
            }
        }
    }
}