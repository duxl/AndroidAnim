package com.duxl.android.anim

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityAnimAttrBinding

/**
 * 属性动画
 */
class AttrAnimActivity : AppCompatActivity(), View.OnClickListener {

    var binding: ActivityAnimAttrBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_anim_attr)
        binding!!.btnValueAnimator.setOnClickListener(this)
        binding!!.btnObjectAnimator.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding!!.btnValueAnimator -> startValueAnim()
            binding!!.btnObjectAnimator -> startObjectAnim()
        }
    }

    private fun startValueAnim() {
        showToast("ValueAnimator演示")
        // 在2秒内，数值变化：1 → 0.1 → 0.5
        val valueAnim = ValueAnimator.ofFloat(1f, 0.1f, 0.5f)
        valueAnim.duration = 2000
        valueAnim.repeatCount = ValueAnimator.INFINITE
        valueAnim.repeatMode = ValueAnimator.REVERSE
        valueAnim.addUpdateListener {
            binding!!.ivImg.alpha = it.animatedValue as Float
        }
        valueAnim.start()
    }

    private fun startObjectAnim() {
        showToast("ObjectAnimator演示")
        // 效果同上面的startValueAnim()
        val objAnim = ObjectAnimator.ofFloat(binding!!.ivImg, "alpha", 1f, 0.1f, 0.5f)
        objAnim.duration = 2000
        objAnim.repeatCount = ValueAnimator.INFINITE
        objAnim.repeatMode = ValueAnimator.REVERSE
        objAnim.start()
    }

    override fun onDestroy() {
        binding!!.ivImg.clearAnimation()
        binding!!.unbind()
        super.onDestroy()
    }

}
