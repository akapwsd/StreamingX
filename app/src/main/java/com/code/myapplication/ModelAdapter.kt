package com.code.myapplication

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.code.bean.ModelBean

class ModelAdapter(mContext: Context, list: ArrayList<ModelBean>?) : RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {
    private var context: Context? = mContext
    private var mList = arrayListOf<ModelBean>()
    private var itemClickListener: ModelItemClickListener? = null

    init {
        this.mList = list!!
    }

    fun addList(list: ArrayList<ModelBean>?) {
        this.mList.addAll(list!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(LayoutInflater.from(context).inflate(R.layout.item_model, parent, false))
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.modelId.text = mList[position].uid.toString()
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(mList[position])
        }
    }


    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var modelId: TextView = itemView.findViewById(R.id.model_id)
    }

    fun setModelItemClickListener(itemClickListener: ModelItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ModelItemClickListener {
        fun onItemClickListener(model: ModelBean)
    }
}