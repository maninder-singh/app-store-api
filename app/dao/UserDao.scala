package dao

import constant.SqlQuery
import models.User
import play.api.db.DB
import play.api.Play.current
import util.JdbcUtil
import scala.collection.mutable.ListBuffer

/**
 * Created by maninders on 5/9/15.
 */
object UserDao {

  def getUserList(): List[User] = {
    var userListBuffer: ListBuffer[User] = new ListBuffer[User]
    val sqlQuery = SqlQuery.GET_ALL_USER
    val connection = DB.getConnection()
    try {
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(sqlQuery)
      while (resultSet.next()) {
        userListBuffer += User(resultSet.getInt("id"), resultSet.getString("name"),
          resultSet.getString("email"), resultSet.getString("contact_number"))
      }
      userListBuffer.toList
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

  def addUser(user: User): Unit = {
    val sqlQuery = SqlQuery.INSERT_USER + user.name + "','" + user.email + "','" +
      user.contactNo + "')"

    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def updateUser(user : User) : Unit = {
    val sqlQuery = "update user set " +
      "name = '" + user.name +"'," +
      "email = '" + user.email + "'," +
      "contact_number = '" + user.contactNo + "' " +
      "where id = " + user.id

    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def deleteUser(id : Int) : Unit = {
    val sqlQuery = SqlQuery.DELETE_USER + id
    JdbcUtil.insertUpdateQuery(sqlQuery)
  }
}