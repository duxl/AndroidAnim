package com.duxl.android.anim.layout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duxl.android.anim.databinding.ActivityRecyclerAnimBinding

class RecyclerAnimActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRecyclerAnimBinding

    val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecyclerAnimBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        //val anim = AnimationUtils.loadAnimation(this, R.anim.my_anim)
        //val anim = AnimationUtils.makeInChildBottomAnimation(this)
        val anim = AnimationUtils.makeInAnimation(this, true)
        val controllerAnim = LayoutAnimationController(anim).apply {
            order = LayoutAnimationController.ORDER_NORMAL
            delay = 0.2f
        }
        val adapter = MyAdapter(list)
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutAnimation = controllerAnim

        mBinding.btnAdd.setOnClickListener {
            mBinding.recyclerView.layoutAnimation = controllerAnim
            repeat(100) {
                list.add("item$it")
            }
            adapter.notifyDataSetChanged()
        }
        mBinding.recyclerView.itemAnimator = DefaultItemAnimator()

        mBinding.btnClear.setOnClickListener {
            list.clear()
            adapter.notifyDataSetChanged()
        }
    }

    private class MyAdapter(var data: List<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(android.R.layout.simple_list_item_1, parent.context)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(android.R.id.text1).text =
                "Position: $position-${data[position]}"
        }

        override fun getItemCount(): Int {
            return data.size
        }

        class MyViewHolder(layoutId: Int, context: Context) :
            RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutId, null)) {

        }
    }
}