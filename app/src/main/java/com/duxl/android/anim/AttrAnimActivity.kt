package com.duxl.android.anim

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        binding!!.btnPropertyValuesHolder.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding!!.btnValueAnimator -> startValueAnim()
            binding!!.btnObjectAnimator -> startObjectAnim()
            binding!!.btnPropertyValuesHolder -> testPropertyValuesHolder()
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

    private fun testPropertyValuesHolder() {
        binding!!.ivImg2.isVisible = true
        // 参考：https://blog.csdn.net/asffghfgfghfg1556/article/details/80443973
        // 定义位移动画
        val translationY = -600f
        val translationYHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, translationY, 0f)
        // 定义旋转动画
        val keyFrame1 = Keyframe.ofFloat(0f, -6f) // 开始时刻逆时针旋转6度
        val keyFrame2 = Keyframe.ofFloat(0.6f, 45f) // 0.6时刻时旋转45度
        val keyFrame3 = Keyframe.ofFloat(1f, 0f) // 结束时旋转0度
        val rotateHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION, keyFrame1, keyFrame2, keyFrame3)

        // 将位移动画和旋转动画组合在一起执行
        ObjectAnimator
            .ofPropertyValuesHolder(binding!!.ivImg2, translationYHolder, rotateHolder)
            .apply { interpolator = AnticipateOvershootInterpolator() }
            .setDuration(2000)
            .start()
    }

    override fun onDestroy() {
        binding!!.ivImg.clearAnimation()
        binding!!.unbind()
        super.onDestroy()
    }

}
