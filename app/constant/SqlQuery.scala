package constant


/**
 * Created by maninders on 5/9/15.
 */
object SqlQuery {

//  Database Operation for AppDao
  val GET_ALL_APPS = "select a.id,a.name,a.description,a.rating,u.id as developer_id,u.name as user_name,u.email," +
    "u.contact_number from app a join user u on a.developer_id = u.id"
  val GET_APP_DETAILS = "select a.id,a.name,a.description,a.rating,u.id as developer_id,u.name as user_name,u.email,u." +
    "contact_number from app a join user u on a.developer_id = u.id where a.id = "
  val INSERT_APP_DETAILS = "insert into app (name,description,rating,developer_id) values ('"
  val DELETE_APP_DETAILS = "delete from app where id = "

//  Database Operation for CommentDao
  val GET_COMMENTS_OF_APP = "select c.id,c.content,c.app_id,c.user_id,u.name as user_name,u.email,u.contact_number from " +
    "comments c join app a on c.app_id = a.id join user u on c.user_id = u.id where c.app_id = "
  val INSERT_NEW_COMMENT = "insert into comments (content,user_id,app_id) values ('"
  val DELETE_COMMENT = "delete from comments where id = "

//  Database Operation for UserDao
  val GET_ALL_USER = "select id,name,email,contact_number from user"
  val INSERT_USER = "insert into user (name,email,contact_number) values ('"
  val DELETE_USER = "delete from user where id = "
}
