package com.nemo.simkl.media

import com.nemo.simkl.SimklAPI
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import com.nemo.simkl.search.searchText

// fields: title, slug, overview, metadata, theater, genres, tmdb
/**
 * Fetch detailed media information from the Simkl API.
 *
 * @param media The media object containing identifiers.
 * @param type The type of media, can be null if derived from the media object.
 * @param extended Whether to fetch extended information for the media.
 * @param fields A list of specific fields to fetch if not fetching extended information.
 *   Valid fields: `title`, `slug`, `overview`, `metadata`, `theater`, `genres`, `tmdb`
 * @return A fully populated StandardMediaObject with the requested details.
 * @throws IllegalArgumentException If required fields or IDs are missing.
 */
suspend fun SimklAPI.media(
  media: StandardMediaObject,
  type: Type? = null,
  extended: Boolean = false,
  fields: List<String> = listOf()
): StandardMediaObject {
  val validFields = listOf("title", "slug", "overview", "metadata", "theater", "genres", "tmdb")
  fields.forEach { if (it !in validFields) throw IllegalArgumentException("Invalid field: $it") }
  val id = media.ids.simkl ?: media.ids.imdb ?: throw IllegalArgumentException("ID cannot be null")
  val endpoint = type?.endpoint ?: media.type?.endpoint ?: throw IllegalArgumentException("Type cannot be null")

  val res = get(
    url = "/$endpoint/$id", params = buildMap {
      put("client_id", API_KEY)
      when (extended) {
        true -> put("extended", "full")
        false -> if (fields.isNotEmpty()) put("extended", fields.joinToString(","))
      }
    }
  )

  return res.parsed<StandardMediaObject>()
}
