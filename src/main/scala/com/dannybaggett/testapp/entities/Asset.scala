package com.dannybaggett.testapp.entities

// common traits for show assets
sealed trait Asset { val id: String; val name: String; val typeId: String; val url: String; val expirationDate: String }
sealed trait Video extends Asset { val format: String; }
sealed trait Advertisement extends Video { val description: String }

// assets
case class ImageAsset(id: String, name: String, url: String, expirationDate: String, typeId: String = "Image")
  extends Asset
case class MovieAsset(id: String, name: String, url: String, expirationDate: String, typeId: String = "Video",
                      format: String = "Movie") extends Video
case class EpisodeAsset(id: String, name: String, url: String, expirationDate: String, typeId: String = "Video",
                        format: String = "Episode") extends Video
case class ClipAsset(id: String, name: String, url: String, expirationDate: String, typeId: String = "Video",
                     format: String = "Clip") extends Video
case class AdvertisementAsset(id: String, name: String, url: String, expirationDate: String, description: String,
                              typeId: String = "Video", format: String = "Advertisement") extends Advertisement