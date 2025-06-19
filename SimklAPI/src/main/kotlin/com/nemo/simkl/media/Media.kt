package com.nemo.simkl.media

import com.nemo.simkl.SimklAPI
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import com.nemo.simkl.search.searchId

/**
 * Fetch detailed media information from the Simkl API.
 *
 * @param media The media object containing identifiers.
 * @param type The type of media, can be null if derived from the media object.
 * @param extended Whether to fetch extended information for the media.
 * @return A StandardMediaObject populated with the requested details.
 * @throws IllegalArgumentException If required fields or IDs are missing.
 */
suspend fun SimklAPI.media(
  media: StandardMediaObject, type: Type? = null, extended: Boolean = false
): StandardMediaObject {
  val id = media.ids.simkl ?: media.ids.imdb
  ?: throw IllegalArgumentException("Id.simkl or Id.imdb cannot be null")
  val endpoint = type?.endpoint ?: media.type?.endpoint ?: throw IllegalArgumentException("Type cannot be null")

  val res = get(
    url = "/$endpoint/$id", params = buildMap {
      put("client_id", API_KEY)
      if (extended) put("extended", "full")
    }
  )

  return res.parsed<StandardMediaObject>()
}

/**
 * Fetch detailed media information from the Simkl API using Simkl ID.
 *
 * @param simklId The Simkl ID for the media item.
 * @param type The type of media, can be null if derived from the media object.
 * @param extended Whether to fetch extended information for the media.
 * @return A StandardMediaObject populated with the requested details.
 * @throws IllegalArgumentException If the media type cannot be determined.
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
 * @param type The type of media, can be null if derived from the media object.
 * @param extended Whether to fetch extended information for the media.
 * @return A StandardMediaObject populated with the requested details.
 * @throws IllegalArgumentException If the media type cannot be determined.
 */
suspend fun SimklAPI.media(
  imdbId: String, type: Type? = null, extended: Boolean = false
): StandardMediaObject {
  val mediaType = type ?: searchId(Id.IMDB, imdbId).firstOrNull()?.type
  ?: throw IllegalArgumentException("Could not determine type of ${Id.IMDB}:$imdbId")
  return media(StandardMediaObject(ids = Id(imdb = imdbId)), mediaType, extended)
}
