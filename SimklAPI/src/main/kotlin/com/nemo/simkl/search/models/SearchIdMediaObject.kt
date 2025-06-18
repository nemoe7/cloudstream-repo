package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject

/**
 * Represents a media object from ID-based search results.
 *
 * @property title The title of the media object, which might be null if unavailable.
 * @property poster A formatted URL pointing to the poster image, if available. The poster URL is
 *   dynamically constructed using a base URL and identifier.
 * @property year The release year of the media object, nullable for unknown or unavailable cases.
 * @property ids A collection of platform-specific or standard identifiers represented by the `Id` class.
 * @property type The type of the media object, which might be null if unavailable.
 * @property status The status of the media item, which can be null if unavailable.
 * @property otherKeys A map holding dynamic properties not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
class SearchIdMediaObject(
  @JsonProperty("title") title: String? = null,
  @JsonProperty("poster") poster: String? = null,
  @JsonProperty("year") year: Int? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonProperty("type") val type: String? = null,
  @JsonProperty("status") val status: Status? = null
) : StandardMediaObject(title, poster, year, ids) {
  override fun toString(): String =
    "SearchIdMediaObject(title=$title, poster=$poster, year=$year, ids=$ids, type=$type, status=$status, otherProperties=$otherKeys)"
}
