package com.nikhil.newsapp.utils.moshiadapters

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class CustomDateAdapter : JsonAdapter<Date>() {
    private val dateFormat = SimpleDateFormat(SERVER_FORMAT, Locale.getDefault())

    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return try {
            val dateAsString = reader.nextString()
            //1st way - not recommended
//            dateFormat.parse(dateAsString)

            //2nd way - Recommended
            val localDateTime = LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern(SERVER_FORMAT).withLocale(Locale.US))
            // LocalDateTime to Date object - default format [Sat Aug 01 10:42:51 IST 2020]
            Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())

            //TODO - Get Date in "dd-MMM-yyyy HH:mm:ss" format
        } catch (e: Exception) {
            Timber.d(e)
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }

    companion object {
        const val SERVER_FORMAT = ("yyyy-MM-dd'T'HH:mm:ss'Z'") // define your server format here
    }

}