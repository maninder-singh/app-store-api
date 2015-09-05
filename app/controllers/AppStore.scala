package controllers

import dao._
import models.{Comment, App, User}
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._

class AppStore extends Controller {

  def apps = Action {
    Ok(Json.toJson(AppDao.getAppList()))
  }

  def details(id: Int) = Action {
    Ok(Json.toJson(AppDao.getAppDetail(id)))
  }

  def users = Action {
    Ok(Json.toJson(UserDao.getUserList()))
  }

  def comments(appId : Int) = Action{
    Ok(Json.toJson(CommentDao.getComment(appId)))
  }

  def addUser = Action(parse.json){
    implicit request => request.body.validate[User] match {
      case JsSuccess(user,_) => {
        UserDao.addUser(user)
        Ok(Json.obj("message" -> "New User added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new user","status" -> "fail"))
    }
  }

  def addApp = Action(parse.json){
    implicit request => request.body.validate[App] match {
      case JsSuccess(app,_) => {
        AppDao.addApp(app)
        Ok(Json.obj("message" -> "New app added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new app into store","status" -> "fail"))
    }
  }

  def addComment = Action(parse.json){
    implicit request => request.body.validate[Comment] match {
      case JsSuccess(comment,_) => {
        CommentDao.addComment(comment)
        Ok(Json.obj("message" -> "New comment to app added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new comment to app","status" -> "fail"))
    }
  }

  def updateUser = Action(parse.json){
    implicit request => request.body.validate[User] match {
      case JsSuccess(user,_) => {
        UserDao.updateUser(user)
        Ok(Json.obj("message" -> "User updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update user","status" -> "fail"))
    }
  }

  def updateApp = Action(parse.json){
    implicit request => request.body.validate[App] match {
      case JsSuccess(app,_) => {
        AppDao.updateApp(app)
        Ok(Json.obj("message" -> "App updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update app","status" -> "fail"))
    }
  }

  def updateComment = Action(parse.json){
    implicit request => request.body.validate[Comment] match {
      case JsSuccess(comment,_) => {
        CommentDao.updateComment(comment)
        Ok(Json.obj("message" -> "App updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update app","status" -> "fail"))
    }
  }

  def deleteApp = Action(parse.json) {
    request => (request.body \ "id").asOpt[Int].map { id =>
      AppDao.deleteApp(id)
      Ok(Json.obj("message" -> "App deleted successfully","status" -> "ok"))
    }.getOrElse {
      BadRequest(Json.obj("message" -> "Unable to delete app","status" -> "fail"))
    }
  }

  def deleteUser = Action(parse.json) {
    request => (request.body \ "id").asOpt[Int].map { id =>
      UserDao.deleteUser(id)
      Ok(Json.obj("message" -> "User deleted successfully","status" -> "ok"))
    }.getOrElse {
      BadRequest(Json.obj("message" -> "Unable to delete user","status" -> "fail"))
    }
  }

  def deleteComment = Action(parse.json) {
    request => (request.body \ "id").asOpt[Int].map { id =>
      CommentDao.deleteComment(id)
      Ok(Json.obj("message" -> "Comment deleted successfully","status" -> "ok"))
    }.getOrElse {
      BadRequest(Json.obj("message" -> "Unable to delete comment","status" -> "fail"))
    }
  }
}