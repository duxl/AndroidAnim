package com.duxl.android.anim

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duxl.android.anim.databinding.ActivityMainBinding
import com.duxl.android.anim.layout.LayoutTransitionAnim01Activity
import com.duxl.android.anim.layout.LayoutTransitionAnim02Activity
import com.duxl.android.anim.layout.RecyclerAnimActivity

/**
 * Android中各种动画演示，参考https://www.jianshu.com/p/0eb89d43eea4
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding!!.btnBuJian.setOnClickListener(this)
        binding!!.btnFrameAnim.setOnClickListener(this)
        binding!!.btnAttrAnim.setOnClickListener(this)
        binding!!.btnRippleEffect.setOnClickListener(this)
        binding!!.btnReveal.setOnClickListener(this)
        binding!!.btnChangeScene.setOnClickListener(this)
        binding!!.btnAnimatedSelector.setOnClickListener(this)
        binding!!.btnAnimatedLayout1.setOnClickListener(this)
        binding!!.btnAnimatedLayout2.setOnClickListener(this)
        binding!!.btnAnimRecycler.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding!!.btnBuJian -> startActivity(Intent(this, BuJianAnimActivity::class.java))
            binding!!.btnFrameAnim -> startActivity(Intent(this, FrameAnimActivity::class.java))
            binding!!.btnAttrAnim -> startActivity(Intent(this, AttrAnimActivity::class.java))
            binding!!.btnRippleEffect -> startActivity(Intent(this, RippleEffectAnimActivity::class.java))
            binding!!.btnReveal -> startActivity(Intent(this, RevealAnimActivity::class.java))
            binding!!.btnChangeScene -> startActivity(Intent(this, ChangeSceneAnimActivity::class.java))
            binding!!.btnAnimatedLayout1 -> startActivity(Intent(this, LayoutTransitionAnim01Activity::class.java))
            binding!!.btnAnimatedLayout2 -> startActivity(Intent(this, LayoutTransitionAnim02Activity::class.java))
            binding!!.btnAnimRecycler -> startActivity(Intent(this, RecyclerAnimActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }
}