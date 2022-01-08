package com.duxl.android.anim

import android.app.Activity
import android.widget.Toast

/**
 * 扩展方法
 * create by duxl 2022/1/7
 */
fun Activity.showToast(msg: CharSequence) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}