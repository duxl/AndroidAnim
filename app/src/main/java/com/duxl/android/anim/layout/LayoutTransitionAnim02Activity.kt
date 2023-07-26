package com.duxl.android.anim.layout

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.duxl.android.anim.R
import com.duxl.android.anim.databinding.ActivityLayoutTransitionAnim02Binding

/**
 * 布局动画-自定义
 * https://www.likecs.com/show-203891267.html
 * LayoutTransition的核心概念是有两种类型的变化会引起四种动画，两种类型的变化分别是add和remove以及对应的VISIBLE以及GONE。以add为例，当add进一个View时，该View有appearing动画，而其他View因该View会发生change-appearing的动画；同理，remove时，被remove掉的View有disappearing动画，而其他View因该View会发生disappearing的动画。
 */
class LayoutTransitionAnim02Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityLayoutTransitionAnim02Binding.inflate(layoutInflater)
    }

    var index = 0
    val layoutTransition = LayoutTransition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        // 开启Layout Animate
        mBinding.container.layoutTransition = layoutTransition

        // 开启/关闭出现动画
        mBinding.inCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutTransition.enableTransitionType(LayoutTransition.APPEARING)
            } else {
                layoutTransition.disableTransitionType(LayoutTransition.APPEARING)
            }
        }

        // 开启/关闭消失动画
        mBinding.inCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutTransition.enableTransitionType(LayoutTransition.DISAPPEARING)
            } else {
                layoutTransition.disableTransitionType(LayoutTransition.DISAPPEARING)
            }
        }


        // 开启/关闭改变出现动画
        mBinding.inCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
            } else {
                layoutTransition.disableTransitionType(LayoutTransition.CHANGE_APPEARING)
            }
        }

        // 开启/关闭改变消失动画
        mBinding.inCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
            } else {
                layoutTransition.disableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
            }
        }

        // 系统动画-自定义动画
        mBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_sys -> {
                    mBinding.container.layoutTransition = layoutTransition
                }

                R.id.rb_custom -> {
                    mBinding.container.layoutTransition = LayoutTransition().also {
                        it.setAnimator(
                            LayoutTransition.APPEARING,
                            ObjectAnimator.ofFloat(null, View.ROTATION_X, 0f, 360f)
                        )
                    }
                }
            }
        }

        // 添加View
        mBinding.btnAddView.setOnClickListener {
            mBinding.container.addView(TextView(LayoutTransitionAnim01Activity@ this).apply {
                gravity = Gravity.CENTER
                setBackgroundColor(if (mBinding.container.childCount % 2 == 0) Color.GRAY else Color.GREEN)
                text = "${mBinding.container.childCount}"
                setPadding(0, 20, 0, 20)
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            })
        }

        // 删除view
        mBinding.btnRemoveView.setOnClickListener {
            if (mBinding.container.childCount > 0) {
                mBinding.container.removeViewAt(0)
            }
        }
    }
}