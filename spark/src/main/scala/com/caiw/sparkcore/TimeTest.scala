package com.caiw.sparkcore

object TimeTest {
  def main(args: Array[String]): Unit = {
    var dataSyncDay = "201601"
    import java.text.SimpleDateFormat
    val format = new SimpleDateFormat("yyyyMM")
    import java.util.Calendar
    val calendar = Calendar.getInstance
    calendar.setTime(format.parse(dataSyncDay))
    calendar.add(Calendar.DATE,1)
    calendar.add(Calendar.MONTH,-1)
    val lastMouth = format.format(calendar.getTime).substring(0,6)
    dataSyncDay = lastMouth


    println(dataSyncDay)
  }
}
