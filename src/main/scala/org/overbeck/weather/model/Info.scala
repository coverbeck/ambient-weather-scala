package org.overbeck.weather.model

import upickle.default.{ReadWriter, macroRW}

case class Info(name: String, coords: Coords)

object Info {
  implicit val rw: ReadWriter[Info] = macroRW
}