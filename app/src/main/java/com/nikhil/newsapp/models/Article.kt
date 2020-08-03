package com.nikhil.newsapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikhil.newsapp.utils.DateTimeUtil
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey
    var id: Int? = 0,
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRequiredDateTimeFormat(): String {
        val localDateTime = LocalDateTime.parse(
            publishedAt,
            DateTimeFormatter.ofPattern(SERVER_FORMAT).withLocale(Locale.US)
        )
        return DateTimeUtil.parseDate(
            Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()).toString(),
            DateTimeUtil.EEEMMMddHHmmsszzzyyyyFormat,
            DateTimeUtil.ddMMMyyyyHHmmssFormat
        ).toString()
    }

    companion object {
        const val SERVER_FORMAT = ("yyyy-MM-dd'T'HH:mm:ss'Z'") // define your server format here
    }
}