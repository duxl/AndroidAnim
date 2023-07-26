package com.duxl.android.anim.layout

import android.animation.LayoutTransition
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.LayoutAnimationController
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.duxl.android.anim.R
import com.duxl.android.anim.databinding.ActivityLayoutTransitionAnim01Binding

/**
 * 布局动画-系统默认
 * https://developer.android.com/training/animation/layout?hl=zh-cn
 */
class LayoutTransitionAnim01Activity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityLayoutTransitionAnim01Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.text = if (isChecked) "关闭动画" else "开启动画"
            mBinding.container.layoutTransition = if (isChecked) LayoutTransition() else null
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
                mBinding.container.removeViewAt(mBinding.container.childCount - 1)
            }
        }
    }
}