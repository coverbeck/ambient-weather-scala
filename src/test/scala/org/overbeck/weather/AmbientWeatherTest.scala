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
      val devices = AmbientWeather(sysProps("appKey"), sysProps("apiKey")).devices
      if (devices.isFailure) println(devices)
      assert(devices.isSuccess)
    }

    val json = Source.fromResource("devices.json").getLines().mkString
    val devices = aw.devices(json)
    assert(devices.size == 1)
    assert(devices(0).macAddress == "EC:FA:BC:4D:45:57")
  }

}
