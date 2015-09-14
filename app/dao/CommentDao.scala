package dao

import constant.SqlQuery
import models.{Comment, User}
import play.api.Play.current
import play.api.db.DB
import util.JdbcUtil

import scala.collection.mutable.ListBuffer

/**
 * Created by maninders on 5/9/15.
 */
object CommentDao {

  def getComment(appId : Int) : List[Comment] = {
    val commentBuffer : ListBuffer[Comment] = new ListBuffer[Comment]
    val sqlQuery =  SqlQuery.GET_COMMENTS_OF_APP + appId + " order by c.id desc"
    val connection = Option(DB.getConnection())

    connection match {
      case Some(con) => {
        val statement = con.createStatement()
        val resultSet = statement.executeQuery(sqlQuery)
        while (resultSet.next()) {
          val user = User(resultSet.getInt("user_id"),resultSet.getString("user_name"),
            resultSet.getString("email"),resultSet.getString("contact_number"))
          commentBuffer += Comment(resultSet.getInt("id"),resultSet.getInt("app_id"),resultSet.getString("content"),user)
        }
        con.close()
        commentBuffer.toList
      }case None => commentBuffer.toList
    }
  }

  def addComment(comment : Comment) : Unit = {
    val sqlQuery = SqlQuery.INSERT_NEW_COMMENT + comment.content + "'," + comment.commentBy.id + "," + comment.appId + ")"
    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def updateComment(comment: Comment) : Unit = {
    val sqlQuery = "update comments set " +
      "content = '" + comment.content +
      "' where id = " + comment.id

    JdbcUtil.insertUpdateQuery(sqlQuery)
  }

  def deleteComment(id : Int) : Unit = {
    val sqlQuery = SqlQuery.DELETE_COMMENT + id
    JdbcUtil.insertUpdateQuery(sqlQuery)
  }
}
