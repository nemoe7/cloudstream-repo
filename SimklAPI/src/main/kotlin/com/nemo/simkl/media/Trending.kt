package com.nemo.simkl.media

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.media.models.ExtendedMediaObject
import com.nemo.simkl.media.models.Interval
import com.nemo.simkl.models.ExtendedFields
import com.nemo.simkl.search.models.Type

/**
 * Fetch trending media items from the Simkl API.
 *
 * NOTE!: according to https://simkl.docs.apiary.io/, you can apply even more filters using
 * the "Best" site section on the website proper.. didnt feel like adding allat
 *
 * @param type The [Type] of media to fetch trending items for.
 * @param interval The [Interval] for which to fetch trending items. (optional)
 * @param fields The [ExtendedFields] to fetch. (optional)
 * @return A list of [ExtendedMediaObject] items that are trending within the given interval.
 */
suspend fun SimklAPI.trending(
  type: Type,
  interval: Interval? = Interval.MONTH,
  vararg fields: ExtendedFields
): List<ExtendedMediaObject> {
  val res = get(
    url = "/${type.endpoint}/trending",
    params = buildMap {
      put("client_id", API_KEY)
      if (interval != null) put("interval", interval.toString())
      if (fields.isNotEmpty()) put("extended", fields.joinToString(","))
    }
  )

  return jacksonObjectMapper().readValue(
    res.text,
    object : TypeReference<List<ExtendedMediaObject>>() {}
  ).map { it.copy(type = type) }
}
