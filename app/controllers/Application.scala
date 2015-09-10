package controllers

import _root_.no.samordnaopptak.apidoc.{AnnotationHelper, ApiDoc, SwaggerUtil}
import _root_.no.samordnaopptak.json.J
import play.api.mvc._

class Application extends Controller {

  val swaggerInfoObject = J.obj(
    "info" -> J.obj(
      "title"   -> "Generated App Store Api",
      "version" -> "1.0"
    )
  )

  @ApiDoc(doc="""
    GET /

    DESCRIPTION
      Default page for app store api
              """)
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  @ApiDoc(doc="""
    GET /api/v1/api-docs

    DESCRIPTION
      Get main swagger json documentation
              """)
  def get() = Action {
    val apidocs = AnnotationHelper.getApiDocsFromAnnotations()
    val generatedSwaggerDocs = SwaggerUtil.getMain("/", apidocs)
    val json = generatedSwaggerDocs ++ swaggerInfoObject
    Ok(json.asJsValue)
  }
} 
