package models

import play.api.libs.json.Json

/**
 * Created by maninders on 4/9/15.
 */
case class User(id : Int,name : String,email : String ,contactNo : String)

object User {
  implicit val userFormat = Json.format[User]
}
