package com.nemo.simkl.search

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nemo.simkl.SimklAPI
import com.nemo.simkl.search.models.SearchIdMediaObject
import com.nemo.simkl.search.models.SearchTextMediaObject
import com.nemo.simkl.search.models.Type

/**
 * Search for a media item by id.
 *
 * @param type The type of media to search for.
 * @param id The id of the media item to search for.
 * @return A list of search results.
 */
suspend fun SimklAPI.searchId(type: String, id: String): List<SearchIdMediaObject> {
  val res = get(
    url = "/search/id", params = mapOf(
      type to id, "client_id" to API_KEY
    )
  )
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<SearchIdMediaObject>>() {})
}

/**
 * Search for a media item by id.
 *
 * @param type The type of media to search for.
 * @param id The id of the media item to search for.
 * @return A list of search results.
 */
suspend fun SimklAPI.searchId(type: String, id: Int): List<SearchIdMediaObject> =
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
): List<SearchTextMediaObject> {
  val res = get(
    url = "/search/$type", params = buildMap {
      put("q", text)
      put("client_id", API_KEY)
      if (extended) put("extended", "full")
    })
  return jacksonObjectMapper().readValue(
    res.text, object : TypeReference<List<SearchTextMediaObject>>() {})
}
