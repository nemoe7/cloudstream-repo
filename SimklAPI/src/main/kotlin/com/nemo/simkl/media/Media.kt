package com.nemo.simkl.media

import com.nemo.simkl.SimklAPI
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import com.nemo.simkl.search.searchId

/**
 * Fetch detailed media information from the Simkl API.
 *
 * @param media A [StandardMediaObject] containing identifiers.
 * @param type [Type] of the media. (optional; defaults to [media]`.type`)
 *   Valid types are: [Type.TV], [Type.ANIME], [Type.MOVIE].
 * @param extended Whether to fetch extended information (optional, defaults to `false`).
 * @return A [StandardMediaObject] populated with the requested details.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], or `null`
 *   or if the requested media could not be found.
 */
suspend fun SimklAPI.media(
  media: StandardMediaObject, type: Type? = null, extended: Boolean = false
): StandardMediaObject {
  val id = media.ids.simkl ?: media.ids.imdb
  ?: throw IllegalArgumentException("Id.simkl or Id.imdb cannot be null")
  val mediaType = type ?: media.type

  when (mediaType) {
    Type.EPISODE -> throw IllegalArgumentException("Type cannot be EPISODE")
    Type.SPECIAL -> throw IllegalArgumentException("Type cannot be SPECIAL")
    null -> throw IllegalArgumentException("Type cannot be null")
    else -> {}
  }

  val res = get(
    url = "/${mediaType.endpoint}/$id", params = buildMap {
      put("client_id", API_KEY)
      if (extended) put("extended", "full")
    })

  return res.parsed<StandardMediaObject>()
}

/**
 * Fetch detailed media information from the Simkl API by Simkl ID.
 *
 * @param simklId The Simkl ID for the media item.
 * @param type [Type] of the media. (recommended; if null, inferred via [SimklAPI.searchId])
 *   Valid types are: [Type.TV], [Type.ANIME], [Type.MOVIE].
 * @param extended Whether to fetch extended information (optional, defaults to `false`).
 * @return A [StandardMediaObject] populated with the requested details.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], or `null`
 *   or if the requested media could not be found.
 */
suspend fun SimklAPI.media(
  simklId: Int, type: Type? = null, extended: Boolean = false
): StandardMediaObject {
  val mediaType =
    type ?: searchId(Id.SIMKL, simklId).firstOrNull()?.type ?: throw IllegalArgumentException(
      "Could not determine type of ${Id.SIMKL}:$simklId"
    )
  return media(StandardMediaObject(ids = Id(simkl = simklId)), mediaType, extended)
}

/**
 * Fetch detailed media information from the Simkl API using IMDB ID.
 *
 * @param imdbId The IMDB ID for the media item.
 * @param type [Type] of the media. (recommended; if null, inferred via [SimklAPI.searchId])
 *   Valid types are: [Type.TV], [Type.ANIME], [Type.MOVIE].
 * @param extended Whether to fetch extended information (optional, defaults to `false`).
 * @return A [StandardMediaObject] populated with the requested details.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], or `null`
 *   or if the requested media could not be found.
 */
suspend fun SimklAPI.media(
  imdbId: String, type: Type? = null, extended: Boolean = false
): StandardMediaObject {
  val mediaType = type ?: searchId(Id.IMDB, imdbId).firstOrNull()?.type
  ?: throw IllegalArgumentException("Could not determine type of ${Id.IMDB}:$imdbId")
  return media(StandardMediaObject(ids = Id(imdb = imdbId)), mediaType, extended)
}
