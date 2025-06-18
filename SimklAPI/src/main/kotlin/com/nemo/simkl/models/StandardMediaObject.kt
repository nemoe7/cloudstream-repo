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
  @JsonProperty("poster") private val _poster: String? = null,
  @JsonProperty("year") val year: Int? = null,
  @JsonProperty("type") open val type: Type? = null,
  @JsonProperty("ids") val ids: Id = Id(),

  @JsonIgnore private val _otherKeys: MutableMap<String, Any?> = mutableMapOf()
) {
  val poster: String? get() = _poster?.let { "$IMAGE_BASE_URL/posters/${it}_m.jpg" }

  val otherKeys: Map<String, Any?> get() = _otherKeys

  @JsonAnySetter
  fun setOtherKeys(key: String, value: Any?) {
    _otherKeys[key] = value
  }

  override fun toString(): String =
    "StandardMediaObject(title=$title, poster=$poster, year=$year, type=$type, ids=$ids, otherKeys=${otherKeys.keys})"
}
