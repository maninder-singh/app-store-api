package models

import play.api.libs.json.Json

/**
 * Created by maninders on 4/9/15.
 */
case class App(id : Int,name : String,description : String,rating : Int,developedBy : User)
//{
//  var id : Int = appId
//  var name : String = appName
//  var description : String = appDescription
//  var rating : Int = appRating
//  var developer : User = developedBy
//}

object App{
  implicit val appFormat = Json.format[App]
}