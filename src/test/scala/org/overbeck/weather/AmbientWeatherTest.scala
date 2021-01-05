package org.overbeck.weather

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
      val devices = authorizedAmbientWeather.devices
      if (devices.isFailure) println(devices)
      assert(devices.isSuccess)
      // TODO: Restore this
//      devices.map(ds => ds.foreach(d => authorizedAmbientWeather.deviceData(d.macAddress)))
    }

    val json = Source.fromResource("devices.json").getLines().mkString
    val devices = aw.devices(json)
    assert(devices.size == 1)
    val macAddress = devices(0).macAddress
    assert(macAddress == "EC:FA:BC:4D:45:57")

    val deviceDataJson = Source.fromResource("deviceData.json").getLines().mkString
    println(aw.deviceDataFromJson(deviceDataJson))
  }

}
