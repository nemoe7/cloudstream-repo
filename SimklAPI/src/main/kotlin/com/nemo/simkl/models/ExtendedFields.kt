package com.nemo.simkl.models

/**
 * Enum representing the fields that can be extended in the Simkl API.
 *
 * @property value The string representation of the field.
 */
enum class ExtendedFields(val value: String) {
  FULL("full"),
  TITLE("title"),
  SLUG("slug"),
  OVERVIEW("overview"),
  METADATA("metadata"),
  THEATER("theater"),
  GENRES("genres"),
  TMDB("tmdb");

  override fun toString(): String = value

  companion object {
    private val map = values().associateBy(ExtendedFields::value)
    fun fromValue(value: String) = map[value]
  }
}
