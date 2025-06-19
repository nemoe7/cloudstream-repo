package com.nemo.simkl.search

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.models.Id
import com.nemo.simkl.search.models.SearchIdResult
import com.nemo.simkl.search.models.SearchTextResult
import com.nemo.simkl.search.models.Type

/**
 * Search for a media item by id.
 *
 * @param source The source of the id to search for.
 * @param id The id of the media item to search for.
 * @return A list of search results.
 */
suspend fun SimklAPI.searchId(source: String, id: String): List<SearchIdResult> {
  if (source !in Id.TYPES) throw IllegalArgumentException("Type must be one of ${Id.TYPES.joinToString(", ")}")
  val res = get(
    url = "/search/id", params = mapOf(
      source to id, "client_id" to API_KEY
    )
  )
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<SearchIdResult>>() {})
}

/**
 * Search for a media item by id.
 *
 * @param type The source of the id to search for.
 * @param id The id of the media item to search for.
 * @return A list of search results.
 */
suspend fun SimklAPI.searchId(type: String, id: Int): List<SearchIdResult> =
  SimklAPI.searchId(type, id.toString())


/**
 * Search for a media item by title.
 *
 * @param type The type of media to search for.
 * @param text The title of the media item to search for.
 * @param extended Whether to fetch extended information for each result.
 * @return A list of search results.
 */
suspend fun SimklAPI.searchText(
  type: Type,
  text: String,
  extended: Boolean = false
): List<SearchTextResult> {
  if (type == Type.EPISODE) throw IllegalArgumentException("Type cannot be EPISODE")
  if (type == Type.SPECIAL) throw IllegalArgumentException("Type cannot be SPECIAL")
  val res = get(
    url = "/search/$type", params = buildMap {
      put("q", text)
      put("client_id", API_KEY)
      if (extended) put("extended", "full")
    })
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<SearchTextResult>>() {})
}
