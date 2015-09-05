package dao

import constant.SqlQuery
import models.{App, User}
import play.api.db.DB
import play.api.Play.current
import util.JdbcUtil
import scala.collection.mutable.ListBuffer

/**
 * Created by maninders on 4/9/15.
 */
object AppDao {

  def getAppList() : List[App] = {
    var appListBuffer: ListBuffer[App] = new ListBuffer[App]
    val sqlQuery = SqlQuery.GET_ALL_APPS
    val connection = DB.getConnection()
    try {
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(sqlQuery)
      while (resultSet.next()) {
        val user = User(resultSet.getInt("developer_id"),resultSet.getString("user_name"),
          resultSet.getString("email"),resultSet.getString("contact_number"))
        var app = App(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                resultSet.getInt("rating"),user)
        appListBuffer += app
      }
      appListBuffer.toList
    } finally {
      if(connection != null){
        connection.close()
      }
    }
  }

  def getAppDetail(appId : Int): App = {

    var appDetail : App = App(0,"","",0,User(0,"","",""))
    val sqlQuery =  SqlQuery.GET_APP_DETAILS + appId

    val connection = DB.getConnection()
    try {
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(sqlQuery)
      while (resultSet.next()) {
        val user = User(resultSet.getInt("developer_id"),resultSet.getString("user_name"),
          resultSet.getString("email"),resultSet.getString("contact_number"))
        appDetail = App(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
          resultSet.getInt("rating"),user)
      }
      appDetail
    } finally {
      if(connection != null){
        connection.close()
      }
    }
  }

  def addApp(app : App) : Unit= {
    val sqlQuery = SqlQuery.INSERT_APP_DETAILS + app.name + "','" + app.description + "','" + app.rating + "'," +
      app.developedBy.id + ")"

    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def updateApp(app : App) : Unit = {
    val sqlQuery = "update app set " +
      "name = '" + app.name +"'," +
      "description = '" + app.description + "'," +
      "rating = " + app.rating + "," +
      "developer_id = " + app.developedBy.id +
      " where id = " + app.id

    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def deleteApp(id : Int) : Unit = {
    val sqlQuery = SqlQuery.DELETE_APP_DETAILS + id
    JdbcUtil.insertUpdateQuery(sqlQuery)
  }
}
