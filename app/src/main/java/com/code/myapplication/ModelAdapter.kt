package com.code.myapplication

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.code.bean.ModelBean

class ModelAdapter : RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {
    private var context: Context? = null
    private var mList = arrayListOf<ModelBean>()
    private var itemClickListener: ModelItemClickListener? = null

    constructor (mContext: Context, list: ArrayList<ModelBean>?) {
        this.context = mContext
        this.mList = list!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(LayoutInflater.from(context).inflate(R.layout.item_model, parent, false))
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
//        holder?.avatar?.text = mList[position]
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }


    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: ImageView = itemView.findViewById(R.id.avatar_iv)
    }

    fun setModelItemClickListener(itemClickListener: ModelItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ModelItemClickListener {
        fun onItemClickListener(position: Int)
    }
}