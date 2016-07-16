 Scala Test App w/ Akka HTTP REST API

This is a scala test app that provides a REST API for returning "show" information
with each show containing any number of image and video assets. Video assets can be
broken down further into clips, episodes, full-movies. A fourth type of video is
available for advertisements which is complete with its own product description.

#### Features

* Akka HTTP for serving pages
* In memory database to store shows and assets
* REST API endpoint for displaying show information in JSON
* json4s for entity marshalling/unmarshalling
* SBT for building app

#### Building

In order to run the application, first cd into the project directory

    sbt run

Get all shows, assuming a browser is being used

    http://localhost:5000/shows

Get a single show

    http://localhost:5000/shows/Show1

#### Future Enhancements
* Frontend UI that consumes the REST API
* REST endpoint to add new shows and assets
* Make use of a proper database
* Make asset URLs target actual assets for viewing