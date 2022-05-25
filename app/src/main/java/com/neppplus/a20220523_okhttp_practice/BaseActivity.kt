package com.neppplus.a20220523_okhttp_practice

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    val TAG = javaClass.simpleName

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar () {
//        기본 액션바를 담아줄 변수 생성
        val defaultActionBar = supportActionBar!!

//        커스텀 모드로 변경 > 우리가 만든 xml로 적용
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_custom_actionbar)

//        양 옆의 여백 제거 -> 모든 영역이 커스텀 뷰
        val myToolbar = defaultActionBar.customView.parent as androidx.appcompat.widget.Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)

        val backBtn = defaultActionBar.customView.findViewById<ImageView>(R.id.backBtn)
        val titleTxt = defaultActionBar.customView.findViewById<TextView>(R.id.titleTxt)
        val profileBtn = defaultActionBar.customView.findViewById<ImageView>(R.id.profileBtn)

        backBtn.setOnClickListener {
            finish()
        }
    }

}