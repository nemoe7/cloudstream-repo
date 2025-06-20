package com.nemo.simkl.models

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.SimklAPI.IMAGE_BASE_URL
import com.nemo.simkl.search.models.Type

/**
 * A media object with standard properties.
 *
 * @property title The title of the media object.
 * @property poster The poster image URL of the media object.
 * @property year The year of the media object.
 * @property type The type of the media object.
 * @property ids The IDs of the media object.
 * @property otherKeys Any additional keys that are not in the standard schema.
 */
open class StandardMediaObject(
  @JsonProperty("title") val title: String? = null,
  @JsonProperty("poster") protected val posterRaw: String? = null,
  @JsonProperty("year") open val year: Int? = null,
  @JsonProperty("type") open val type: Type? = null,
  @JsonProperty("ids") val ids: Id = Id(),

  @JsonIgnore private val _otherKeys: MutableMap<String, Any?> = mutableMapOf()
) {
  open fun poster(
    suffix: PosterSuffix = PosterSuffix.M, extension: ImageExtension = ImageExtension.JPG
  ): String = "$IMAGE_BASE_URL/$posterRaw$suffix$extension"

  val otherKeys: Map<String, Any?> get() = _otherKeys

  @JsonAnySetter
  fun setOtherKeys(key: String, value: Any?) {
    _otherKeys[key] = value
  }

  fun containsKey(key: String): Boolean = _otherKeys.containsKey(key)

  fun copy(
    title: String? = this.title,
    posterRaw: String? = this.posterRaw,
    year: Int? = this.year,
    type: Type? = this.type,
    ids: Id = this.ids,
    otherKeys: Map<String, Any?> = this.otherKeys
  ) = StandardMediaObject(
    title = title,
    posterRaw = posterRaw,
    year = year,
    type = type,
    ids = ids,
    _otherKeys = otherKeys.toMutableMap()
  )

  operator fun get(key: String): Any? = _otherKeys[key]

  override fun toString(): String =
    "StandardMediaObject(title=$title, poster=$posterRaw, year=$year, type=$type, ids=$ids, otherKeys=${otherKeys.keys})"
}
