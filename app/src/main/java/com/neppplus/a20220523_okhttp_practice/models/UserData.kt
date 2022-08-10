package com.neppplus.a20220523_okhttp_practice.models

import org.json.JSONObject

class UserData {

    var id = 0
    var email = ""
    var nickname = ""

    companion object {
        fun getUserDataFromJson(jsonObj : JSONObject) : UserData {

            val userData = UserData()

            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickname = jsonObj.getString("nick_name")

            return userData
        }
    }
}