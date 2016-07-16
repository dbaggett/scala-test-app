package com.dannybaggett.testapp.routes

import akka.http.scaladsl.marshalling.{ToResponseMarshaller, ToResponseMarshallable}

import scala.concurrent.{ExecutionContext, Future}
import akka.http.scaladsl.server.{Directives, Route}

import com.dannybaggett.testapp.serialization.JsonSupport

trait BaseRoute extends Directives with JsonSupport {

  implicit def executionContext: ExecutionContext

  def complete[T: ToResponseMarshaller](resource: Future[Option[T]]): Route =
    onSuccess(resource) {
      case Some(t) => complete(ToResponseMarshallable(t))
      case None => complete(404, None)
    }

  def complete(resource: Future[Unit]): Route = onSuccess(resource) { complete(204, None) }

}