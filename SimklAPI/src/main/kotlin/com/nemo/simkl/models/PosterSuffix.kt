package com.nemo.simkl.models

import com.nemo.simkl.search.models.Type

/**
 * Enum class representing the possible poster suffixes and their corresponding sizes.
 *
 * Poster suffixes are used to specify the size of the poster image in the Simkl API.
 *
 * @property value The value of the poster suffix.
 * @property width The width of the poster image, in pixels.
 * @property height The height of the poster image, in pixels.
 */
enum class PosterSuffix(val value: String, val width: Int, val height: Int) {
  W("w", 600, 338),
  M("m", 340, 0),
  CA("ca", 190, 279),
  C("c", 170, 250),
  CM("cm", 84, 124),
  S("s", 40, 57);

  override fun toString(): String = value

  companion object {
    private val map = PosterSuffix.entries.associateBy(PosterSuffix::value)
    fun fromValue(value: String) = map[value]
  }
}
