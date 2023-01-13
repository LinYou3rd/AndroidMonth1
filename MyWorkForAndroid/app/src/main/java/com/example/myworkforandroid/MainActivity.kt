package com.example.myworkforandroid

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.up_item.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val upList=ArrayList<Up>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUps()


        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        recycleView.layoutManager=layoutManager

        val adapter=UpAdapter(upList,textView,imageView)
        recycleView.adapter=adapter

        val intent=Intent(this,DetailActivity::class.java)
        adapter.setOnLongClick(object:UpAdapter.OnLongClick{
            override fun click(up: Up) {
                intent.putExtra("upId",up.imageId)
                intent.putExtra("upFan",up.funs)
                intent.putExtra("upName",up.name)
                startActivityForResult(intent,1)
            }
        })

    }


    private fun initUps(){
        upList.add(Up("up1",R.drawable.up1,123))
        upList.add(Up("up2",R.drawable.up2,788888))
        upList.add(Up("up3",R.drawable.up3,10045))
        upList.add(Up("up4",R.drawable.up4,500))
        upList.add(Up("up5",R.drawable.up5,460000))
        upList.add(Up("up6",R.drawable.up6,99999))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1-> if (resultCode == -1) {
                    val name = data?.getStringExtra("death")
                    if (name != null) {
                        val newList=removeUpList(name)
                    }
                    val adapter = UpAdapter(upList, textView, imageView)
                    adapter.notifyDataSetChanged()
                    recycleView.adapter=adapter
                } else {
                    val adapter = UpAdapter(upList, textView, imageView)
                    adapter.notifyDataSetChanged()
                    recycleView.adapter=adapter
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeUpList(upName:String):List<Up>{
        upList.removeIf{it.name==upName}
        val newList= mutableListOf<Up>()
        newList.addAll(upList)
        return newList
    }
}