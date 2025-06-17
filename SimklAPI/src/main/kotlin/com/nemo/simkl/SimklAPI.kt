package com.nemo.simkl

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.lagradost.api.Log
import com.lagradost.cloudstream3.app
import com.lagradost.nicehttp.NiceResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SimklAPI {
  private var userAccessToken: String? = null
  private const val BASE_URL = "https://api.simkl.com/"
  private val headers = mapOf(
    "Content-Type" to "application/json",
    "Authorization" to "Bearer ${BuildConfig.TEMP_AT}",
    "simkl-api-key" to BuildConfig.SIMKL_API_KEY
  )

  fun init(context: Context? = null) {
    val sharedPreferences = context?.getSharedPreferences(
      BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE
    )

    userAccessToken = sharedPreferences?.getString("userToken", null)
  }

  suspend fun get(url: String = "", params: Map<String, String> = emptyMap()): NiceResponse {
    Log.d("SimklAPI", "GET $url, headers: $headers, params: $params")
    val response = app.get(
      url = BASE_URL + url, headers = headers, params = params
    )
    return response
  }

  @RequiresApi(Build.VERSION_CODES.O)
  internal fun String.toDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  internal fun LocalDateTime.toDateTimeString(): String {
    return this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
  }
}
