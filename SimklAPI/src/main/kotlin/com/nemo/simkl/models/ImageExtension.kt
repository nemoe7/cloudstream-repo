package com.nemo.simkl.models

/**
 * Enum class representing the possible image file extensions.
 *
 * This enum is used to specify the file extension of images retrieved from the Simkl API.
 *
 * @property value The string representation of the image file extension.
 */
enum class ImageExtension(private val value: String) {
  JPG("jpg"),
  WEBP("webp");

  override fun toString(): String = value

  companion object {
    private val map = ImageExtension.entries.associateBy(ImageExtension::value)
    fun fromValue(value: String) = map[value]
  }
}
