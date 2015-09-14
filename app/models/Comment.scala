package models

import play.api.libs.json.Json

/**
 * Created by maninders on 4/9/15.
 */
case class Comment(id : Int,appId : Int,content : String,commentBy : User)

object Comment {
  implicit val commentFormat = Json.format[Comment]
}
