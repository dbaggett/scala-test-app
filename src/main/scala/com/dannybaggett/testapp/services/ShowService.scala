package com.dannybaggett.testapp.services

import com.dannybaggett.testapp.entities.Show

import scala.concurrent.{ExecutionContext, Future}

class ShowService(implicit val executionContext: ExecutionContext) {
  var shows = Vector.empty[Show]

  def getAllShows = shows

  def getShow(id: String): Future[Option[Show]] = Future {
    shows.find(_.id == id)
  }
}