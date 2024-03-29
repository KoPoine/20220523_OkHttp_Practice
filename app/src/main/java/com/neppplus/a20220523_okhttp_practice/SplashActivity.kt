package com.neppplus.a20220523_okhttp_practice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.neppplus.a20220523_okhttp_practice.models.UserData
import com.neppplus.a20220523_okhttp_practice.utils.ContextUtil
import com.neppplus.a20220523_okhttp_practice.utils.GlobalData
import com.neppplus.a20220523_okhttp_practice.utils.ServerUtil
import org.json.JSONObject

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        var isTokenOk = false

        ServerUtil.getRequestUserInfo(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val code = jsonObj.getInt("code")
//                isTokenOk = (code == 200)
                if (code == 200) {
                    isTokenOk = true
                    val dataObj = jsonObj.getJSONObject("data")
                    val userObj = dataObj.getJSONObject("user")

                    GlobalData.loginUser = UserData.getUserDataFromJson(userObj)
                }
            }

        })

        myHandler.postDelayed({
//            자동로그인을 해도 되는가?
//           1) 사용자가 자동로그인을 한다고 했는가?
            val isAutoLoginOk = ContextUtil.getAutoLogin(mContext)


//           2) token이 있는가? (토큰의 유효성 Text)
//           2-1) 저장된 토큰이 있는지?

//           2-2) 그 토큰이 유효한가?

            val myIntent : Intent

            if (isAutoLoginOk && isTokenOk) {
//                둘다 ok 바로 메인화면
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)

            }
            startActivity(myIntent)
            finish()


        }, 2500)
    }
}