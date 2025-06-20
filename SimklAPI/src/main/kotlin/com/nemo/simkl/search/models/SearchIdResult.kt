package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject

/**
 * Represents a media object from ID-based search results.
 *
 * @property title The title of the media object.
 * @property poster The poster image URL of the media object.
 * @property year The year of the media object.
 * @property type The type of the media object.
 * @property ids The IDs of the media object.
 * @property status The status of the media item, which can be null if unavailable.
 * @property otherKeys A map holding dynamic properties not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
class SearchIdResult(
  @JsonProperty("title") title: String? = null,
  @JsonProperty("poster") posterRaw: String? = null,
  @JsonProperty("year") year: Int? = null,
  @JsonProperty("type") type: Type? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonProperty("status") val status: Status? = null
) : StandardMediaObject(title, posterRaw, year, type, ids) {
  override fun toString(): String =
    "SearchIdResult(" +
      "title=$title, " +
      "poster=$posterRaw, " +
      "year=$year, " +
      "type=$type, " +
      "ids=$ids, " +
      "status=$status, " +
      "otherKeys=${otherKeys.keys}" +
      ")"
}
