package org.overbeck.weather

import upickle.default.{macroRW, read, ReadWriter}

import scala.util.{Failure, Success, Try}

class AmbientWeather(private val appKey: String, private val apiKey: String) {

  private val baseUrl = "https://api.ambientweather.net/v1"
  private val devicesUrl = s"${baseUrl}/devices"

  def devices: Try[Seq[Device]] = {
    try {
      Success(devices(requests.get(devicesUrl, params = Map("applicationKey" -> appKey, "apiKey" -> apiKey))
        .text))
    }
    catch {
      case e: Throwable => Failure(e)
    }
  }

  def devices(json: String): Seq[Device] = read[Seq[Device]](json)
}

object AmbientWeather {
  def apply(appKey: String, apiKey: String) = new AmbientWeather(appKey, apiKey)
}

case class Info(name: String, coords: Coords)

object Info {
  implicit val rw: ReadWriter[Info] = macroRW
}

case class Coords(geo: Geo, elevation: Float, location: String, address: String, coords: CoordsPair)

object Coords {
  implicit val rw: ReadWriter[Coords] = macroRW
}

case class CoordsPair(lon: Float, lat: Float)

case class Geo(coordinates: Array[Float], `type`: String)

object Geo {
  implicit val rw: ReadWriter[Geo] = macroRW
}

case class LastData( dateutc: Long,
                     tempinf: Float,
                     humidityin: Int,
                     baromrelin: Float,
                     baromabsin: Float,
                     tempf: Float,
                     battout: Int,
                     humidity: Int,
                     winddir: Int,
                     windspeedmph: Float,
                     windgustmph: Float,
                     maxdailygust: Float,
                     hourlyrainin: Float,
                     eventrainin: Float,
                     dailyrainin: Float,
                     weeklyrainin: Float,
                     monthlyrainin: Float,
                     totalrainin: Float,
                     solarradiation: Float,
                     uv: Int,
                     feelsLike: Float,
                     dewPoint: Float,
                     feelsLikein: Float,
                     dewPointin: Float,
                     lastRain: String,
                     tz: String,
                     date: String
                   )

object LastData {
  implicit val rw: ReadWriter[LastData] = macroRW
}

case class Device(macAddress: String, lastData: LastData, info: Info)

object Device {
  implicit val rw: ReadWriter[Device] = macroRW
}

object CoordsPair {
  implicit val rw: ReadWriter[CoordsPair] = macroRW
}
