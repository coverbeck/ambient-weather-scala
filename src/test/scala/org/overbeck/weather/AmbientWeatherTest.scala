package org.overbeck.weather

import org.overbeck.weather.model.DeviceData
import utest.Tests

import scala.io.Source
import scala.jdk.CollectionConverters._

object AmbientWeatherTest extends utest.TestSuite {
  val tests = Tests {
    val aw = AmbientWeather("one", "two")
    assert(aw.devices.isFailure)

    val env = System.getenv.asScala
    val ambientApiKey = "AMBIENT_API_KEY"
    val ambientAppKey = "AMBIENT_APP_KEY"
    if (env.contains(ambientApiKey) && env.contains(ambientAppKey)) {
      val authorizedAmbientWeather = AmbientWeather(env(ambientAppKey), env(ambientApiKey))
      val devicesTry = authorizedAmbientWeather.devices
      if (devicesTry.isFailure) println(devicesTry)
      assert(devicesTry.isSuccess)
      devicesTry.map(devices => devices.foreach(d => println(authorizedAmbientWeather.deviceData(d.macAddress))))
    }

    val json = Source.fromResource("devices.json").getLines().mkString
    val devices = aw.devices(json)
    assert(devices.size == 1)
    val macAddress = devices(0).macAddress
    assert(macAddress == "EC:FA:BC:4D:45:57")

    val deviceDataJson = Source.fromResource("deviceData.json").getLines().mkString
    val deviceData: Seq[DeviceData] = aw.deviceDataFromJson(deviceDataJson)
    assert(deviceData.size == 2)
  }

}
