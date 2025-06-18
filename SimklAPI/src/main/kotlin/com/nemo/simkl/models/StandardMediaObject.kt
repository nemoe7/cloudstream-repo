package com.nemo.simkl.models

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.SimklAPI.IMAGE_BASE_URL

/**
 * Represents a standard media object in the Simkl API.
 *
 * @param title The title of the media item.
 * @param poster The poster image URL of the media item.
 * @param year The year of the media item.
 * @param ids The IDs of the media item.
 */
open class StandardMediaObject(
  @JsonProperty("title") val title: String? = null,
  @JsonProperty("poster") private val _poster: String? = null,
  @JsonProperty("year") val year: Int? = null,
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
    "StandardMediaObject(title=$title, poster=$poster, year=$year, ids=$ids, otherProperties=$otherKeys)"
}
