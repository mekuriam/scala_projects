package com.example

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport.defaultNodeSeqMarshaller
import akka.http.scaladsl.server.{ HttpApp, PathMatcher, Route }
import spray.json.{ DefaultJsonProtocol, JsValue }

case class AncillaryData(caseNumber: String, payload: JsValue)

object AncillaryDataJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val ancillaryDataFormat = jsonFormat2(AncillaryData)
}
/**
 * Server will be started calling `WebServerHttpApp.startServer("localhost", 8080)`
 * and it will be shutdown after pressing return.
 *
 *
 */
object WebServerHttpApp extends HttpApp with App {

  import AncillaryDataJsonSupport._

  def routes: Route =
    pathEndOrSingleSlash { // Listens to the top `/`
      complete("Server up and running") // Completes with some text
    } ~
      pathPrefix("case_details") {
        get {
          path(Segment) { caseNumber =>
            complete(s"Say hello to akka-http $caseNumber")
          }
        }

      } ~ post {
        entity(as[AncillaryData]) { ancillaryData => complete(ancillaryData) }
      }

  // This will start the server until the return key is pressed
  startServer("localhost", 8080)
}
