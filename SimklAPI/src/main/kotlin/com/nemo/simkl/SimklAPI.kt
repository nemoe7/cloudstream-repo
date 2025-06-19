package com.nemo.simkl

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.lagradost.api.Log
import com.lagradost.cloudstream3.app
import com.lagradost.nicehttp.NiceResponse
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object SimklAPI {
  internal const val API_KEY = BuildConfig.SIMKL_API_KEY
  internal const val IMAGE_BASE_URL = "https://wsrv.nl/?url=https://simkl.in"

  private const val LIB_PKG_NAME = BuildConfig.LIBRARY_PACKAGE_NAME
  private const val BASE_URL = "https://api.simkl.com"
  private var userAccessToken: String? = null

  private val headers get() = mapOf(
    "Content-Type" to "application/json",
    "Authorization" to "Bearer $userAccessToken",
    "simkl-api-key" to API_KEY
  )

  fun init(context: Context? = null) {
    val sharedPreferences = context?.getSharedPreferences(
      LIB_PKG_NAME, Context.MODE_PRIVATE
    )

    userAccessToken = sharedPreferences?.getString("userAccessToken", null)
  }

  suspend fun get(url: String = "", params: Map<String, String> = emptyMap()): NiceResponse {
    val response = app.get(
      url = BASE_URL + url, headers = headers, params = params
    )
    Log.d("SimklAPI", "GET ${response.url} (${response.code}): $response")
    return response
  }

  @RequiresApi(Build.VERSION_CODES.O)
  internal fun String.toDateTime(): LocalDateTime {
    val odt = OffsetDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return odt.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
  }

  @RequiresApi(Build.VERSION_CODES.O)
  internal fun LocalDateTime.toDateTimeString(): String {
    return this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
  }
}
