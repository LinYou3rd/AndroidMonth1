package com.example.myworkforandroid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class UpAdapter(val upList: List<Up>, val textView: TextView, val imageView: ImageView):RecyclerView.Adapter<UpAdapter.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val upImage:ImageView=view.findViewById(R.id.upImage)
        val upName:TextView=view.findViewById(R.id.upName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.up_item,parent,false)
        val viewHolder=ViewHolder(view)

        viewHolder.upName.setOnClickListener{
            val position=viewHolder.adapterPosition
            val up=upList[position]
            clickDisplay(up)
        }

        viewHolder.upImage.setOnClickListener{
            val position=viewHolder.adapterPosition
            val up=upList[position]
            clickDisplay(up)
        }

        return viewHolder
    }

    fun clickDisplay(up: Up){
        textView.setText("${up.name} 的视频动态")
        when(up.imageId){
            R.drawable.up1->imageView.setImageResource(R.drawable.star1)
            R.drawable.up2->imageView.setImageResource(R.drawable.star2)
            R.drawable.up3->imageView.setImageResource(R.drawable.star3)
            R.drawable.up4->imageView.setImageResource(R.drawable.star4)
            R.drawable.up5->imageView.setImageResource(R.drawable.star5)
            R.drawable.up6->imageView.setImageResource(R.drawable.star6)
        }
    }



    interface OnLongClick{
        fun click(up: Up)
    }

    var clickListener:OnLongClick?=null

    fun setOnLongClick(listener: OnLongClick){
        clickListener=listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val up=upList[position]
        holder.upImage.setImageResource(up.imageId)
        holder.upName.text=up.name
        holder.upImage.setOnLongClickListener {
            clickListener?.click(up)
            true
        }
    }

    override fun getItemCount()=upList.size
}