package com.nemo.simkl.media.models

/**
 * Enum representing the possible image suffixes and their corresponding sizes
 * for episodes.
 *
 * Image suffixes are used to specify the size of the episode image in the Simkl API.
 *
 * @property value The value of the episode image suffix.
 * @property width The width of the episode image, in pixels.
 * @property height The height of the episode image, in pixels.
 */
enum class EpisodeImageSuffix(val value: String, val width: Int, val height: Int) {
  W("w", 600, 338),
  C("c", 210, 118),
  M("m", 112, 63);

  override fun toString(): String = value

  companion object {
    private val map = EpisodeImageSuffix.entries.associateBy(EpisodeImageSuffix::value)
    fun fromValue(value: String) = map[value]
  }
}
