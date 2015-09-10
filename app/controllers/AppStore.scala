package controllers

import _root_.no.samordnaopptak.apidoc.ApiDoc
import dao._
import models.{App, Comment, User}
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._

class AppStore extends Controller {

  @ApiDoc(doc="""
    GET /apps

    DESCRIPTION
      Get all apps in api-store

    RESULT
      400 : Any <- Syntax Error
              """)
  def apps = Action {
    Ok(Json.toJson(AppDao.getAppList()))
  }

  @ApiDoc(doc="""
    GET /apps/{id}

    DESCRIPTION
      Get app information based on id

    PARAMETERS
      id: Int <- The app id

    RESULT
      400 : Any <- Syntax Error
              """)
  def details(id: Int) = Action {
    Ok(Json.toJson(AppDao.getAppDetail(id)))
  }

  @ApiDoc(doc="""
    GET /users

    DESCRIPTION
      Get all user in api-store

    RESULT
      400 : Any <- Syntax Error
              """)
  def users = Action {
    Ok(Json.toJson(UserDao.getUserList()))
  }

  @ApiDoc(doc="""
    GET /comments/{appId}

    DESCRIPTION
      Get comments based on app id

    PARAMETERS
      appId: Int <- The app id

    RESULT
      400 : Any <- Syntax Error
              """)
  def comments(appId : Int) = Action{
    Ok(Json.toJson(CommentDao.getComment(appId)))
  }

  @ApiDoc(doc="""
    POST /users

    DESCRIPTION
      Register new user

    PARAMETERS
      body:User

    User:
      id: Int(body)             <- User id
      name: String(body)        <- User name
      email: String(body)       <- User email id
      contactNo: String(body)   <- User contact No.

    RESULT
      400 : Any <- Syntax Error
              """)
  def addUser = Action(parse.json){
    implicit request => request.body.validate[User] match {
      case JsSuccess(user,_) => {
        UserDao.addUser(user)
        Ok(Json.obj("message" -> "New User added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new user","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    POST /apps

    DESCRIPTION
      Add new app

    PARAMETERS
      body:App

    App:
      id: Int(body)                 <- App id
      name: String(body)            <- App name
      description: String(body)     <- App description
      rating: Int(body)             <- App rating
      developedBy: User(optional)   <- App developer

    RESULT
      400 : Any <- Syntax Error
              """)
  def addApp = Action(parse.json){
    implicit request => request.body.validate[App] match {
      case JsSuccess(app,_) => {
        AppDao.addApp(app)
        Ok(Json.obj("message" -> "New app added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new app into store","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    POST /comments

    DESCRIPTION
      Add new comment into app

    PARAMETERS
      body:Comment

    Comment:
      id: Int(body)                 <- Comment id
      appId: Int(body)              <- Comment app id
      content: String(body)         <- Comment content
      commentBy: User(optional)     <- Comment user

    RESULT
      400 : Any <- Syntax Error
              """)
  def addComment = Action(parse.json){
    implicit request => request.body.validate[Comment] match {
      case JsSuccess(comment,_) => {
        CommentDao.addComment(comment)
        Ok(Json.obj("message" -> "New comment to app added successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to add new comment to app","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    PUT /users

    DESCRIPTION
      Update user

    PARAMETERS
      body:User

    RESULT
      400 : Any <- Syntax Error
              """)
  def updateUser = Action(parse.json){
    implicit request => request.body.validate[User] match {
      case JsSuccess(user,_) => {
        UserDao.updateUser(user)
        Ok(Json.obj("message" -> "User updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update user","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    PUT /apps

    DESCRIPTION
      Add new app

    PARAMETERS
      body:App

    RESULT
      400 : Any <- Syntax Error
              """)
  def updateApp = Action(parse.json){
    implicit request => request.body.validate[App] match {
      case JsSuccess(app,_) => {
        AppDao.updateApp(app)
        Ok(Json.obj("message" -> "App updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update app","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    PUT /comments

    DESCRIPTION
      Update comment of app

    PARAMETERS
      body:Comment

    RESULT
      400 : Any <- Syntax Error
              """)
  def updateComment = Action(parse.json){
    implicit request => request.body.validate[Comment] match {
      case JsSuccess(comment,_) => {
        CommentDao.updateComment(comment)
        Ok(Json.obj("message" -> "App updated successfully","status" -> "ok"))
      }
      case JsError(errors) => BadRequest(Json.obj("message" -> "Unable to update app","status" -> "fail"))
    }
  }

  @ApiDoc(doc="""
    DELETE /apps/{id}

    DESCRIPTION
      Delete app

    PARAMETERS
      id: Int <- The app id

    RESULT
      400 : Any <- Syntax Error
              """)
  def deleteApp(id : Int) = Action {
    AppDao.deleteApp(id)
    Ok(Json.obj("message" -> "App deleted successfully","status" -> "ok"))
  }

  @ApiDoc(doc="""
    DELETE /users/{id}

    DESCRIPTION
      Delete user

    PARAMETERS
      id: Int   <-  User id to be delete

    RESULT
      400 : Any <- Syntax Error
              """)
  def deleteUser(id : Int) = Action {
    UserDao.deleteUser(id)
    Ok(Json.obj("message" -> "User deleted successfully","status" -> "ok"))
  }

  @ApiDoc(doc="""
    DELETE /comments/{id}

    DESCRIPTION
      Delete comment of paticular app

    PARAMETERS
      id: Int <- The app id

    RESULT
      400 : Any <- Syntax Error
              """)
  def deleteComment(id : Int) = Action {
    CommentDao.deleteComment(id)
    Ok(Json.obj("message" -> "Comment deleted successfully","status" -> "ok"))
  }
}