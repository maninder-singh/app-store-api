package util

import play.api.Play.current
import play.api.db.DB

/**
 * Created by maninders on 5/9/15.
 */


object JdbcUtil {

  def insertUpdateQuery(sqlQuery : String) : Unit = {

    val connection = Option(DB.getConnection())

    connection match {
      case Some(con) => {
        val statement = con.createStatement()
        statement.executeUpdate(sqlQuery)
        con.close()
      }
      case None =>
    }
  }
}
