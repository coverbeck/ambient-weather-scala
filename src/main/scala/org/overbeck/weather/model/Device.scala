package org.overbeck.weather.model

import upickle.default.{ReadWriter, macroRW}

case class Device(macAddress: String, lastData: LastData, info: Info)

object Device {
  implicit val rw: ReadWriter[Device] = macroRW
}