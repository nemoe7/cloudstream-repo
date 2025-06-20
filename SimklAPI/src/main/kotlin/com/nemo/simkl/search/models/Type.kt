package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Enum representing the type of media that can be searched in the Simkl API.
 *
 * This enum is used in the `search` endpoint to specify the type of media that should be searched.
 *
 * The following types are supported:
 * - `tv`: TV shows
 * - `anime`: Anime
 * - `movie`: Movies
 *
 * The `endpoint` property returns the endpoint that should be used when searching the specified type.
 */
enum class Type(private val value: String, val endpoint: String) {
  @JsonAlias("show")
  @JsonProperty("tv")
  TV("tv", "tv"),

  @JsonProperty("anime")
  ANIME("anime", "anime"),

  @JsonAlias("movies")
  @JsonProperty("movie")
  MOVIE("movie", "movies"),

  @JsonProperty("episode")
  EPISODE("episode", "episodes"),

  @JsonProperty("special")
  SPECIAL("special", "episodes");

  override fun toString(): String = value

  companion object {
    private val map = entries.associateBy(Type::value)
    fun fromValue(value: String) = map[value]
  }
}
