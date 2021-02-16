package org.overbeck.weather.model

import upickle.default.{ReadWriter, macroRW}

case class LastData(dateutc: Long,
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