# ambient-weather-scala

A Scala library for the Ambient Weather API.

There is already a [Ambient Java API](https://github.com/rsv-code/ambient-weather-java), but I wanted to try it in Scala.

## Usage

You need an API key and an App key from Ambient Weather
```
 val appKey = "12345"
 val apiKey = "abcde"
 val aw = AmbientWeather(appKey, apiKey)
 # Get all weather devices
 val devicesTry: Try[Seq[DeviceData]] = aw.devices
 devicesTry
    .map(devices => devices
        .foreach(device => println(aw.deviceData(device.macAddress))))
```

## Limitations

* Using [uPickle](https://github.com/lihaoyi/upickle) for JSON serialization, which has an out of the box limit of 64 fields in a 
case class. [Device Data](https://github.com/ambient-weather/api-docs/wiki/Device-Data-Specs) can have more than 64 fields, so I just
  commented out several.
* I only have one Ambient Weather Device to try this on, so the Option fields are the ones that don't appear for my device, the
WS-2902C.
* No logging