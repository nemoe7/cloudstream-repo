package com.nemo.simkl.media

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.media.models.Episode
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import com.nemo.simkl.search.searchId

/**
 * Fetch episodes for a given media item.
 *
 * @param media The media item to fetch episodes for.
 * @param type The type of media, which can be null if derived from the media object.
 * @return A list of episodes.
 * @throws IllegalArgumentException If required fields or IDs are missing.
 */
suspend fun SimklAPI.episode(
  media: StandardMediaObject, type: Type? = null
): List<Episode> {
  val id = media.ids.simkl ?: throw IllegalArgumentException("Id.simkl cannot be null")
  val endpoint =
    type?.endpoint ?: media.type?.endpoint ?: throw IllegalArgumentException("Type cannot be null")
  if (endpoint == Type.EPISODE.endpoint) throw IllegalArgumentException("Type cannot be EPISODE")
  if (endpoint == Type.SPECIAL.endpoint) throw IllegalArgumentException("Type cannot be SPECIAL")
  if (endpoint == Type.MOVIE.endpoint) throw IllegalArgumentException("Type cannot be MOVIE")

  val res = get(
    url = "/$endpoint/${Type.EPISODE.endpoint}/$id", params = mapOf(
      "client_id" to API_KEY, "extended" to "full"
    )
  )
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<Episode>>() {})
}

/**
 * Fetch episodes for a given media item.
 *
 * @param simklId The Simkl ID of the media item to fetch episodes for.
 * @param type The type of media, which can be null if derived from the media object.
 * @return A list of episodes.
 * @throws IllegalArgumentException If required fields or IDs are missing.
 */
suspend fun SimklAPI.episode(simklId: Int, type: Type? = null): List<Episode> {
  val mediaType =
    type ?: searchId(Id.SIMKL, simklId).firstOrNull()?.type ?: throw IllegalArgumentException(
      "Could not determine type of ${Id.SIMKL}:$simklId"
    )

  return episode(StandardMediaObject(ids = Id(simkl = simklId)), mediaType)
}

/**
 * Fetch episodes for a given media item.
 *
 * @param imdbId The IMDb ID of the media item to fetch episodes for.
 * @param type The type of media, which can be null if derived from the media object.
 * @return A list of episodes.
 * @throws IllegalArgumentException If required fields or IDs are missing.
 */
suspend fun SimklAPI.episode(imdbId: String, type: Type? = null): List<Episode> {
  val mediaType =
    type ?: searchId(Id.IMDB, imdbId).firstOrNull()?.type ?: throw IllegalArgumentException(
      "Could not determine type of ${Id.IMDB}:$imdbId"
    )

  return episode(StandardMediaObject(ids = Id(imdb = imdbId)), mediaType)
}
