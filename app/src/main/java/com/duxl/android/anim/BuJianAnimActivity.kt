package com.duxl.android.anim

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityAnimBujianBinding

/**
 * 补间动画
 * create by duxl 2022/1/7
 */
class BuJianAnimActivity : AppCompatActivity(), View.OnClickListener {

    var binding: ActivityAnimBujianBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_anim_bujian)
        binding!!.btnAnimAlpha.setOnClickListener(this)
        binding!!.btnAnimRotate.setOnClickListener(this)
        binding!!.btnAnimScale.setOnClickListener(this)
        binding!!.btnAnimTranslate.setOnClickListener(this)
        binding!!.btnAnimSet.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding!!.btnAnimAlpha -> animAlpha()
            binding!!.btnAnimRotate -> animRotate()
            binding!!.btnAnimScale -> animScale()
            binding!!.btnAnimTranslate -> animTranslate()
            binding!!.btnAnimSet -> animSet()
        }
    }

    private fun animAlpha() {
        showToast("渐变动画")
        val anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.duration = 1500 // 动画执行时间
        //anim.fillAfter = true // 动画结束后保持结束后的状态
        binding!!.ivImg.startAnimation(anim)
    }

    private fun animRotate() {
        showToast("旋转动画")
        val anim = AnimationUtils.loadAnimation(this, R.anim.rotate)
        anim.duration = 1500
        anim.fillAfter = true
        binding!!.ivImg.startAnimation(anim)
    }

    private fun animScale() {
        showToast("缩放动画")
        val anim = AnimationUtils.loadAnimation(this, R.anim.scale)
        anim.duration = 1500
        anim.repeatMode = Animation.REVERSE // 动画反转
        anim.repeatCount = Animation.INFINITE // 无限循环动画
        binding!!.ivImg.startAnimation(anim)
    }

    private fun animTranslate() {
        showToast("位移动画")
        val anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.duration = 1500
        anim.fillAfter = true
        binding!!.ivImg.startAnimation(anim)
    }

    private fun animSet() {
        // ps: 不允许set动画设置重复
        showToast("多种动画叠加")
        val anim = AnimationUtils.loadAnimation(this, R.anim.set)
        binding!!.ivImg.startAnimation(anim)
    }

    override fun onDestroy() {
        // 好的编程习惯是，页面销毁是取消动画
        binding!!.ivImg.clearAnimation()
        binding!!.unbind()
        super.onDestroy()

    }
}