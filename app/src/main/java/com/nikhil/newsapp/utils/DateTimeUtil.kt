package com.nikhil.newsapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    var hhmmFormat: SimpleDateFormat = SimpleDateFormat("hh:mm", Locale.US) // 01:55


    var HHmmFormat: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.US) // 13:55


    var HHmmssFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.US) // 13:55:22


    var ampmFormat: SimpleDateFormat = SimpleDateFormat("a", Locale.US) // PM


    var ddMMyyyyFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US) // 31-08-2018


    var ddMMyyyHHmmFormat: SimpleDateFormat =
        SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US) // 31-08-2018 13:55


    var yyyyMMddFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US) // 2018-08-31


    var ddMMMyyyyhhmmampmFormat: SimpleDateFormat =
        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US) // 31 Aug 2018, 01:55 PM


    var yyyMMddHHmmssFormat: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US) // 2018-08-31 01:55:22


    var EEEddMMMyyyyFormat: SimpleDateFormat =
        SimpleDateFormat("EEE dd-MMM-yyyy", Locale.US) // Fri 31-Aug-2018


    var EEEddFormat: SimpleDateFormat = SimpleDateFormat("EEE dd", Locale.US) // Fri 31


    var MMMMyyyyFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.US) // August 2018


    var zoneFormat: SimpleDateFormat = SimpleDateFormat("Z", Locale.US) // +0530


    var zoneShortFormat: SimpleDateFormat = SimpleDateFormat("z", Locale.US) // GMT+05:30


    var zoneLongFormat: SimpleDateFormat = SimpleDateFormat("zzzz", Locale.US) // India Standard Time


    var MMMFormat: SimpleDateFormat = SimpleDateFormat("MMM", Locale.US) // Aug


    var MMFormat: SimpleDateFormat = SimpleDateFormat("MM", Locale.US) // 08


    var ddFormat: SimpleDateFormat = SimpleDateFormat("dd", Locale.US) // 31


    var EEEEFormat: SimpleDateFormat = SimpleDateFormat("EEEE", Locale.US) // Friday


    var MMMddFormat: SimpleDateFormat = SimpleDateFormat("MMM dd", Locale.US) // Aug 31


    var EEEEddMMMyyyyFormat: SimpleDateFormat =
        SimpleDateFormat("EEEE dd MMM, yyyy", Locale.US) // Friday 31 Aug, 2018


    var ddMMMMyyyyFormat: SimpleDateFormat =
        SimpleDateFormat("dd MMMM, yyyy", Locale.US) // 31 August, 2018


    var hhmmampmFormat: SimpleDateFormat = SimpleDateFormat("hh:mm a", Locale.US) // 01:55 PM


    var MMddyyyyFormat: SimpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US) // 08/31/2018

    val EEEMMMddHHmmsszzzyyyyFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
    val ddMMMyyyyHHmmssFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US)

    fun parseDate(
        inputDateString: String?,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDateString
    }

}
