package com.nikhil.newsapp.source.remote.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.nikhil.newsapp.utils.DateTimeUtil
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@JsonClass(generateAdapter = true)
data class GetNewsResponseEntity(
    val status: String? = "",
    val totalResults: Int,
    val articles: List<Article>? = null
) {
    data class Article(
        val source: Source? = null,
        val author: String? = "",
        val title: String? = "",
        val description: String? = "",
        val url: String? = "",
        val urlToImage: String? = "",
        val publishedAt: String? = "",
        val content: String? = ""
    ) {
        data class Source(
            val id: String? = "",
            val name: String? = ""
        )

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
}