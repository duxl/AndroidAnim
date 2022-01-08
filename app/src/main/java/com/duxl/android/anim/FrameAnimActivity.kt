package com.duxl.android.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityAnimFrameBinding

class FrameAnimActivity : AppCompatActivity() {

    var binding: ActivityAnimFrameBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_anim_frame)
        val ivImg = binding!!.ivImg
        ivImg.setBackgroundResource(R.drawable.frame_anim)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // 特别注意，AnimationDrawable 的 start() 方法不能在 Activity 的 onCreate 方法中调运，
            // 因为 AnimationDrawable 还未完全附着到 window 上， 所以最好的调运时机是 onWindowFocusChanged() 方法中
            val anim = binding!!.ivImg.background as AnimationDrawable
            anim.start()
        }
    }

    override fun onDestroy() {
        binding!!.ivImg.clearAnimation()
        super.onDestroy()
    }
}
