package com.nemo.simkl.media

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.media.models.Episode
import com.nemo.simkl.models.ExtendedFields
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import com.nemo.simkl.search.searchId

/**
 * Fetch episodes for a given media item.
 *
 * @param media A [StandardMediaObject] containing identifiers.
 * @param type [Type] of the media. (optional; defaults to [media]`.type`)
 *   Valid types are: [Type.TV], [Type.ANIME].
 * @param fields [ExtendedFields] to specify which additional data to include. (optional)
 * @return A list of [Episode] objects.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], [Type.MOVIE],
 *   or `null` or if the requested media could not be found.
 */
suspend fun SimklAPI.episode(
  media: StandardMediaObject, type: Type? = null, vararg fields: ExtendedFields
): List<Episode> {
  val id = media.ids.simkl ?: throw IllegalArgumentException("Id.simkl cannot be null")
  val mediaType = type ?: media.type ?: throw IllegalArgumentException("Type cannot be null")
  when (mediaType) {
    Type.EPISODE, Type.SPECIAL, Type.MOVIE ->
      throw IllegalArgumentException("Type cannot be ${mediaType.name}")
    else -> {}
  }

  val endpoint = mediaType.endpoint

  val res = get(
    url = "/$endpoint/${Type.EPISODE.endpoint}/$id", params = buildMap {
      put("client_id", API_KEY)
      put("extended", fields.joinToString(","))
    }
  )
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<Episode>>() {})
}

/**
 * Fetch episodes for a given media item.
 *
 * @param simklId The Simkl ID of the media item to fetch episodes for.
 * @param type [Type] of the media. (recommended; if null, inferred via [SimklAPI.searchId])
 *   Valid types are: [Type.TV], [Type.ANIME].
 * @param fields [ExtendedFields] to specify which additional data to include. (optional)
 * @return A list of episodes.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], [Type.MOVIE],
 * or `null` or if the requested media could not be found.
 */
suspend fun SimklAPI.episode(
  simklId: Int,
  type: Type? = null,
  vararg fields: ExtendedFields
): List<Episode> {
  val mediaType =
    type ?: searchId(Id.SIMKL, simklId).firstOrNull()?.type ?: throw IllegalArgumentException(
      "Could not determine type of ${Id.SIMKL}:$simklId"
    )

  return episode(StandardMediaObject(ids = Id(simkl = simklId)), mediaType, *fields)
}

/**
 * Fetch episodes for a given media item.
 *
 * @param imdbId The IMDb ID of the media item to fetch episodes for.
 * @param type [Type] of the media. (recommended; if null, inferred via [SimklAPI.searchId])
 *   Valid types are: [Type.TV], [Type.ANIME].
 * @param fields [ExtendedFields] to specify which additional data to include. (optional)
 * @return A list of episodes.
 * @throws IllegalArgumentException If [type] is [Type.EPISODE], [Type.SPECIAL], [Type.MOVIE],
 *   or `null` or if the requested media could not be found.
 */
suspend fun SimklAPI.episode(
  imdbId: String,
  type: Type? = null,
  vararg fields: ExtendedFields
): List<Episode> {
  val mediaType =
    type ?: searchId(Id.IMDB, imdbId).firstOrNull()?.type ?: throw IllegalArgumentException(
      "Could not determine type of ${Id.IMDB}:$imdbId"
    )

  return episode(StandardMediaObject(ids = Id(imdb = imdbId)), mediaType, *fields)
}
