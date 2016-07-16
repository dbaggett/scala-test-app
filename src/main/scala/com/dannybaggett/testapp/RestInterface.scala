package com.dannybaggett.testapp

import scala.concurrent.ExecutionContext

import akka.http.scaladsl.server.Route

import com.dannybaggett.testapp.resources.ShowResource
import com.dannybaggett.testapp.services.ShowService

trait RestInterface extends ShowResource {

  implicit def executionContext: ExecutionContext

  lazy val showService = new ShowService

  val routes: Route = showRoutes

}