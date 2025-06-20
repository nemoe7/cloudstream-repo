package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Enum representing the status of a media item.
 *
 * Possible values:
 * - ended
 * - tba
 * - airing
 * - released
 * - upcoming
 */
enum class Status(private val value: String) {
  @JsonAlias("aired")
  @JsonProperty("ended")
  ENDED("ended"),

  @JsonProperty("tba")
  TBA("tba"),

  @JsonProperty("ongoing")
  ONGOING("ongoing"),

  @JsonProperty("airing")
  AIRING("airing"),

  @JsonAlias("premiere")
  @JsonProperty("released")
  RELEASED("released"),

  @JsonProperty("upcoming")
  UPCOMING("upcoming");

  override fun toString(): String = value

  companion object {
    private val map = entries.associateBy(Status::value)
    fun fromValue(value: String) = map[value]
  }
}
