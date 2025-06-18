package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject

/**
 * Represents a media object from text-based search results.
 *
 * @property title The title of the media object, which might be null if unavailable.
 * @property poster A formatted URL pointing to the poster image, if available. The poster URL is
 *   dynamically constructed using a base URL and identifier.
 * @property year The release year of the media object, nullable for unknown or unavailable cases.
 * @property ids A collection of platform-specific or standard identifiers represented by the `Id` class.
 * @property status The status of the media item, which can be null if unavailable.
 * @property epCount The number of episodes in the media object, which might be null if unavailable.
 * @property endpointType The type of endpoint for the media object, which might be null if unavailable.
 * @property otherKeys A map holding dynamic properties not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
class SearchTextMediaObject(
  @JsonProperty("title") title: String? = null,
  @JsonProperty("poster") poster: String? = null,
  @JsonProperty("year") year: Int? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonProperty("status") val status: Status? = null,
  @JsonProperty("ep_count") val epCount: Int? = null,
  @JsonProperty("endpoint_type") val endpointType: Type
) : StandardMediaObject(title, poster, year, endpointType, ids) {
  @JsonIgnore
  override val type: Type = endpointType

  override fun toString(): String =
    "SearchTextMediaObject(title=$title, poster=$poster, year=$year, ids=$ids, status=$status, epCount=$epCount, endpointType=$endpointType, otherProperties=${otherKeys.keys})"
}
