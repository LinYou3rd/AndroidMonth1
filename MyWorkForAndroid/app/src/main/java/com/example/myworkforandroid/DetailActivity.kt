package com.example.myworkforandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val upId = intent.getIntExtra("upId",1)
        imageViewInDetail.setImageResource(upId)
        val upName = intent.getStringExtra("upName")
        val upFan = intent.getIntExtra("upFan",1)
        textViewInDetail.text="up：$upName      up粉丝数：$upFan"

        var flag=1//如果取消关注再退出，则再次setResult，于是添加变量判定是否取关
        noSeeLonger.setOnClickListener{
            flag=0
            Toast.makeText(this,"取关成功",Toast.LENGTH_SHORT).show()
            val intent=Intent()
            intent.putExtra("death",upName)
            setResult(RESULT_OK,intent)
        }

        finish.setOnClickListener{
            if (flag==0){
                finish()
            }
            else{
                val intent=Intent()
                setResult(RESULT_CANCELED,intent)
                finish()
            }
        }
    }

}