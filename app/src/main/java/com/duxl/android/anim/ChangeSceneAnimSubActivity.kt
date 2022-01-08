package com.duxl.android.anim

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityChangeSceneAnimBinding
import com.duxl.android.anim.databinding.ActivityChangeSceneAnimSubBinding

/**
 * 转场动画&共享元素
 */
class ChangeSceneAnimSubActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeSceneAnimSubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_scene_anim_sub)

        binding.ivImg.setImageResource(intent.getIntExtra("img", 0))
        binding.tvText.text = intent.getStringExtra("txt")

    }
}