package com.dannybaggett.testapp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.dannybaggett.testapp.entities._

import com.typesafe.config.ConfigFactory

object Main extends App with RestInterface {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem("scala-test")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val api = routes

  // bootstrap the database
  val showWithEverything = Show(
    "Show1",
    "Test Show 1",
    "A test show with all assets",
    List(
      ImageAsset("Image1", "Test Image", "/image/is/here", "2016-07-16 00:00:00"),
      MovieAsset("Movie1", "Test Movie", "/movie/is/here", "2016-07-16 00:00:00"),
      EpisodeAsset("Episode1", "Test Episode", "/episode/is/here", "2016-07-16 00:00:00"),
      ClipAsset("Clip1", "Test Clip", "/clip/is/here", "2016-07-16 00:00:00"),
      AdvertisementAsset(
        "Advertisement1",
        "Test Advertisement",
        "/advertisement/is/here",
        "2016-07-16 00:00:00",
        "Product X")
    )
  )
  val showWithTwoImages = Show(
    "Show2",
    "Test Show 2",
    "A test show with only two images",
    List(
      ImageAsset("Image1", "Test Image", "/image/is/here", "2016-07-16 00:00:00"),
      ImageAsset("Image1", "Test Image", "/image/is/here", "2016-07-16 00:00:00")
    )
  )
  val showWithNoAssets = Show("Show3", "Test Show 3", "A test show with no assets", List())

  showService.shows = Vector(showWithEverything, showWithTwoImages, showWithNoAssets)

  Http().bindAndHandle(handler = api, interface = host, port = port) map { binding =>
    println(s"Server started at ${binding.localAddress}") } recover { case ex =>
    println(s"Server could not bind to $host:$port", ex.getMessage)
  }
}