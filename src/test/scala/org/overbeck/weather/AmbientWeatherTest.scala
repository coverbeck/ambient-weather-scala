package org.overbeck.weather

import org.overbeck.weather.model.DeviceData
import utest.Tests

import scala.io.Source
import scala.jdk.CollectionConverters._

object AmbientWeatherTest extends utest.TestSuite {
  val tests = Tests {
    val aw = AmbientWeather("one", "two")
    assert(aw.devices.isFailure)

    val sysProps = System.getProperties.asScala
    if (sysProps.contains("apiKey") && sysProps.contains("appKey")) {
      val authorizedAmbientWeather = AmbientWeather(sysProps("appKey"), sysProps("apiKey"))
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
