package com.duxl.android.anim

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityAnimRippleEffectBinding

class RippleEffectAnimActivity : AppCompatActivity(), View.OnClickListener {

    //var binding: ActivityAnimRippleEffectBinding? = null
    private lateinit var binding: ActivityAnimRippleEffectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_anim_ripple_effect)
        binding.btnCustom.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding!!.btnCustom -> showToast("自定义（有边界）")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}
