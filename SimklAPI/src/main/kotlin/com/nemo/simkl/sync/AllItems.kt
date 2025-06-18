package com.nemo.simkl.sync

import android.os.Build
import androidx.annotation.RequiresApi
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.SimklAPI.toDateTimeString
import com.nemo.simkl.sync.models.Extended
import com.nemo.simkl.sync.models.Response
import com.nemo.simkl.sync.models.Status
import com.nemo.simkl.sync.models.Type
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
suspend fun SimklAPI.allItems(
  type: Type? = null,
  status: Status? = null,
  dateFrom: LocalDateTime? = null,
  extended: Extended? = null,
  episodeWatchedAt: Boolean = false,
  memos: Boolean = false
): Response {
  val params = buildMap {
    dateFrom?.let { put("date_from", it.toDateTimeString()) }
    extended?.let { put("extended", it.value) }
    if (episodeWatchedAt) put("episode_watched_at", "yes")
    if (memos) put("memos", "yes")
  }
  return get(
    url = "/sync/all-items" + "/${type ?: ""}" + "/${status ?: ""}", params = params
  ).parsed<Response>()
}
