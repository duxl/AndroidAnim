package com.duxl.android.anim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityLayoutAnimatedSelectorBinding

/**
 * 控件从一个状态改变成另外一种状态，中间的过渡动画
 * 参考：https://blog.csdn.net/wangsen927/article/details/94781945
 */
class AnimatedSelectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutAnimatedSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_layout_animated_selector);
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}
