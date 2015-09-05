package models

import play.api.libs.json.Json

/**
 * Created by maninders on 4/9/15.
 */
case class Comment(id : Int,appId : Int,content : String,commentBy : User)
//{
//  var id : Int = commentId
//  var appId : Int = appid
//  var content : String = commentData
//  var user : User = commentBy
//}


object Comment {
  implicit val commentFormat = Json.format[Comment]
}