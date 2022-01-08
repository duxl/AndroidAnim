package com.duxl.android.anim

import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityAnimRevealBinding

/**
 * 揭露动画
 */
class RevealAnimActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityAnimRevealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_anim_reveal)
        binding.btnShowLeftTop.setOnClickListener(this)
        binding.btnShowCenter.setOnClickListener(this)
        binding.ivImg.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnShowLeftTop -> showLeftTop()
            binding.btnShowCenter -> showCenter()
        }
    }

    private fun showLeftTop() {
        showAnim(0, 0)
    }

    private fun showCenter() {
        val centerX = binding.ivImg.measuredWidth / 2
        val centerY = binding.ivImg.measuredHeight / 2
        showAnim(centerX, centerY)
    }

    private fun showAnim(centerX: Int, centerY: Int) {
        binding.ivImg.visibility = View.VISIBLE
        // 计算控件对角线长度 开方（W平方+H平方）
        val endRadius = Math.hypot(binding.ivImg.measuredWidth.toDouble(), binding.ivImg.measuredHeight.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(binding.ivImg, centerX, centerY, 0f, endRadius)
        anim.duration = 800
        anim.start()
    }
}
