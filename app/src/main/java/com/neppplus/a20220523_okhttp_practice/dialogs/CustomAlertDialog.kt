package com.neppplus.a20220523_okhttp_practice.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import com.neppplus.a20220523_okhttp_practice.databinding.CustomAlertDialogBinding

class CustomAlertDialog (val context : Context, val activity : Activity) {

//    다이얼로그 객체 변수에 생성
    private val dialog = Dialog(context)

    lateinit var binding : CustomAlertDialogBinding

    fun myDialog (title : String, body: String, onClickListener : ButtonClickListener) {
        binding = CustomAlertDialogBinding.inflate(activity.layoutInflater)
        dialog.setContentView(binding.root)

//        다이얼로그 외곽 클릭시 취소 될건지 아닌지.
        dialog.setCancelable(true)

//        다이얼로그 가로 / 세로 비율 정리
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        binding.titleTxt.text = title
        binding.bodyTxt.text = body

        binding.positiveBtn.setOnClickListener {
            onClickListener.positiveBtnClicked()
        }

        binding.negativeBtn.setOnClickListener {
            onClickListener.negativeBtnClicked()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun positiveBtnClicked()
        fun negativeBtnClicked()
    }

}