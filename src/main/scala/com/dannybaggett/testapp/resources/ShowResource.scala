package com.dannybaggett.testapp.resources

import akka.http.scaladsl.server.Route

import com.dannybaggett.testapp.services.ShowService
import com.dannybaggett.testapp.routes.BaseRoute

trait ShowResource extends BaseRoute {

  val showService: ShowService

  def showRoutes: Route = pathPrefix("shows") {
    pathEnd {
      get {
        complete(showService.getAllShows)
      }
    } ~
      path(Segment) { id =>
        get{
          complete(showService.getShow(id))
        }
      }
  }

}