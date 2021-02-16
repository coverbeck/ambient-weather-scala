package org.overbeck.weather.model

import upickle.default.{ReadWriter, macroRW}

case class Coords(geo: Geo, elevation: Float, location: String, address: String, coords: CoordsPair)

object Coords {
  implicit val rw: ReadWriter[Coords] = macroRW
}

case class CoordsPair(lon: Float, lat: Float)

case class Geo(coordinates: Array[Float], `type`: String)

object Geo {
  implicit val rw: ReadWriter[Geo] = macroRW
}

object CoordsPair {
  implicit val rw: ReadWriter[CoordsPair] = macroRW
}
