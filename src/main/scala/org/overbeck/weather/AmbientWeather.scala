package org.overbeck.weather

import org.overbeck.weather.model.{Device, DeviceData}
import upickle.default.read

import scala.util.{Failure, Success, Try}

class AmbientWeather(private val appKey: String, private val apiKey: String) {

  private val baseUrl = "https://api.ambientweather.net/v1"
  private val devicesUrl = s"${baseUrl}/devices"
  private val authMap = Map("applicationKey" -> appKey, "apiKey" -> apiKey)


  def devices: Try[Seq[Device]] = {
    try {
      Success(devices(requests.get(devicesUrl, params = authMap).text))
    }
    catch {
      case e: Throwable => Failure(e)
    }
  }

  def devices(json: String): Seq[Device] = read[Seq[Device]](json)

  def deviceData(macAddress: String, limit: Int = 288, endDateMs: String = ""): Try[Seq[DeviceData]] = {
    val deviceDataUrl = s"${devicesUrl}/${macAddress}"
    val map = authMap + ("limit" -> limit.toString, "endDate" -> endDateMs)
    Success(deviceDataFromJson(requests.get(deviceDataUrl, params = map).text))
  }

  def deviceDataFromJson(json: String): Seq[DeviceData]= OptionPickler.read[Seq[DeviceData]](json)
}

object AmbientWeather {
  def apply(appKey: String, apiKey: String) = new AmbientWeather(appKey, apiKey)
}



