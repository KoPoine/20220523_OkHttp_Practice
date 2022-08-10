package com.neppplus.a20220523_okhttp_practice.models

import org.json.JSONObject
import java.text.SimpleDateFormat

class ReplyData {

    var id = 0
    var content = ""
    var user = UserData()
    var createdAt = ""
    var likeCount = 0
    //    어느 진영에 대한 댓글?
    var selectedSide = SideData()
    var dislikeCount = 0
    var myLike = false
    var myDislike = false
    var replyCount = 0



    companion object {
        fun getReplyDataFromJson(jsonObj: JSONObject) : ReplyData {
            val replyData = ReplyData()

            replyData.id = jsonObj.getInt("id")
            replyData.content = jsonObj.getString("content")

            val userObj = jsonObj.getJSONObject("user")
            replyData.user = UserData.getUserDataFromJson(userObj)

            val sideObj = jsonObj.getJSONObject("selected_side")
            replyData.selectedSide =  SideData.getSideDataFromJson(sideObj)

            replyData.createdAt = jsonObj.getString("created_at")

////            작성일시 파싱 -> String으로 서버가 내려줌. (String으로 임시 추출)
//            val createdAtStr = jsonObj.getString("created_at")
//
////            임시 추출 (String -> Calendar) 형태로 변환 -> 댓글데이터의 작성일시로 반영.
//
////            서버가 준 String을 분석하기 위한 SimpleDateFormat 만들기
//            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//
////            parse로 Date형태의 일시 생성 -> Calendar변수.time 에 대입.
//            replyData.createdAt.time = sdf.parse(createdAtStr)


//            좋아요 / 싫어요 / 댓글 갯수 등등

            replyData.likeCount = jsonObj.getInt("like_count")
            replyData.dislikeCount = jsonObj.getInt("dislike_count")
            replyData.myLike = jsonObj.getBoolean("my_like")
            replyData.myDislike = jsonObj.getBoolean("my_dislike")
            replyData.replyCount = jsonObj.getInt("reply_count")

            return replyData
        }
    }


}