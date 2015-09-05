package util

import play.api.db.DB
import play.api.Play.current

/**
 * Created by maninders on 5/9/15.
 */


object JdbcUtil {

  def insertUpdateQuery(sqlQuery : String) : Unit = {

    val connection = DB.getConnection()
    try {
      val statement = connection.createStatement()
      statement.executeUpdate(sqlQuery)
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }
}
