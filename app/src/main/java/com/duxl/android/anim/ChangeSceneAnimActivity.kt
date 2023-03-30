package com.duxl.android.anim

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.duxl.android.anim.databinding.ActivityChangeSceneAnimBinding

/**
 * 转场动画&共享元素
 * create by duxl 2022/1/8
 */
class ChangeSceneAnimActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityChangeSceneAnimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_scene_anim)
        binding.ivImg.setOnClickListener(this)
        /*
        为共享元素设置上transtionName。设置name的时候两个界面都要添加上，这样才可以检测到是哪两个元素共享。
        但是我只设置了第二个页面的transtionName也能生效，第一个不用设置
         */
        //binding.ivImg.transitionName = "myImgAnim"

        // ps: 不要关闭硬件加速，否则动画效果有影响。非要关闭硬件加速请设置到单个Activity上面
        // 参考 https://developer.android.com/guide/topics/graphics/hardware-accel?hl=zh-cn
        // ps: 关于转场动画返回时无效，页面关闭不要调用finish(),请使用onBackPressed()

        showAdapter()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivImg -> {
                val intent = Intent(this, ChangeSceneAnimSubActivity::class.java)
                intent.putExtra("img", R.drawable.test)
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    binding.ivImg,
                    "myImgAnimBig"
                )
                startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    private fun showAdapter() {
        val items = arrayListOf(
            Pair(R.drawable.run1, "任何技术都有可能被淘汰，只有不断学习才能在社会上立足"),
            Pair(R.drawable.run2, "机会总是留给那些有准备的人"),
            Pair(R.drawable.run3, "没有捷径，脚踏实地才是最好的路"),
            Pair(R.drawable.run4, "随时都要有危机感，安于现状是很危险的做法"),
            Pair(R.drawable.run5, "无助、无能、无法，什么都没有的时候只能绝望")
        )
        binding.recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                return object :
                    RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.adapter, null)) {}
            }

            override fun getItemCount() = items.size

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                holder.itemView.findViewById<ImageView>(R.id.ivImg).setImageResource(items[position].first)
                holder.itemView.findViewById<TextView>(R.id.tvText).text = items[position].second

                holder.itemView.setOnClickListener {
                    toDetail(it, position)
                }
            }

            val toDetail: (View, Int) -> Unit = {v, position ->
                val intent = Intent(this@ChangeSceneAnimActivity, ChangeSceneAnimSubActivity::class.java)
                intent.putExtra("img", items[position].first)
                intent.putExtra("txt", items[position].second)

                val pairImg = androidx.core.util.Pair(v.findViewById<View>(R.id.ivImg), "myImgAnimBig")
                val pairTxt = androidx.core.util.Pair(v.findViewById<View>(R.id.tvText), "myTxtAnimDetail")
                val optionCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ChangeSceneAnimActivity, pairImg, pairTxt)
                startActivity(intent, optionCompat.toBundle())
            }
        }
    }
}