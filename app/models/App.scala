package models

import play.api.libs.json.Json

/**
 * Created by maninders on 4/9/15.
 */
case class App(id : Int,name : String,description : String,rating : Int,developedBy : User)

object App{
  implicit val appFormat = Json.format[App]
}