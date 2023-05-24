package com.code.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.bean.ModelBean
import com.code.bean.ModelListBean
import com.code.listener.HttpRequestListener
import com.code.listener.RequestModelListListener
import com.code.utils.HttpRequestUtils
import com.code.youyu.api.RtcManager
import kotlinx.android.synthetic.main.activity_model_list.*

class ModelListActivity : Activity() {
    private var list: ArrayList<ModelBean>? = arrayListOf()
    private var adapter: ModelAdapter? = null
    private val channelId = "2825806909305530098"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_list)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        model_rv.layoutManager = layoutManager
        model_rv.itemAnimator = DefaultItemAnimator()
        adapter = ModelAdapter(this, list)
        model_rv.adapter = adapter
        adapter?.setModelItemClickListener(object : ModelAdapter.ModelItemClickListener {
            override fun onItemClickListener(model: ModelBean) {
                val uid = model.uid
                RtcManager.getInstance().callVideo(channelId, "110", object : HttpRequestListener {
                    override fun requestSuccess(o: Any?) {
                        startActivity(Intent(this@ModelListActivity, AgoVideoActivity::class.java))
                    }

                    override fun requestError(code: Int, error: String?) {
                        Toast.makeText(this@ModelListActivity, error, Toast.LENGTH_LONG).show()
                    }
                })
            }
        })

        HttpRequestUtils.getInstance().getModelList(this, 0, 0, 50, object : RequestModelListListener {
            override fun onResult(data: ModelListBean) {
                runOnUiThread {
                    list = data.list
                    adapter?.addList(list)
                    adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(code: Int, reason: String?) {

            }
        })
    }
}