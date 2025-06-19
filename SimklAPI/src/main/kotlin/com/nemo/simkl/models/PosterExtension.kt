package com.nemo.simkl.models

import com.nemo.simkl.search.models.Type

/**
 * Enum class representing the possible poster file extensions.
 *
 * Poster extensions define the format of the poster image that can be used in the Simkl API.
 *
 * @property value The file extension value.
 */
enum class PosterExtension(private val value: String) {
  JPG("jpg"),
  WEBP("webp");

  override fun toString(): String = value

  companion object {
    private val map = PosterExtension.entries.associateBy(PosterExtension::value)
    fun fromValue(value: String) = map[value]
  }
}
