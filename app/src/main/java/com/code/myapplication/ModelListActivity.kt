package com.code.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.bean.ModelBean
import kotlinx.android.synthetic.main.activity_model_list.*

class ModelListActivity : Activity() {
    private var list: ArrayList<ModelBean>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_list)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        model_rv.layoutManager = layoutManager
        model_rv.itemAnimator = DefaultItemAnimator()
        val adapter = ModelAdapter(this, list)
        model_rv.adapter = adapter
        adapter.setModelItemClickListener(object : ModelAdapter.ModelItemClickListener {
            override fun onItemClickListener(position: Int) {

            }
        })
    }
}