package com.nemo.simkl.search.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject

/**
 * Represents a media object from text-based search results.
 *
 * @property title The title of the media object.
 * @property poster The poster image URL of the media object.
 * @property year The year of the media object.
 * @property type The type of the media object.
 * @property ids The IDs of the media object.
 * @property status The status of the media item, which can be null if unavailable.
 * @property epCount The number of episodes in the media object, which might be null if unavailable.
 * @property rank The rank of the media object, which might be null if unavailable.
 * @property url The URL of the media object, which might be null if unavailable.
 * @property allTitles A list of all available titles for the media object, which might be null if unavailable.
 * @property otherKeys A map holding dynamic properties not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
class SearchTextResult(
  @JsonProperty("title") title: String? = null,
  @JsonProperty("poster") posterRaw: String? = null,
  @JsonProperty("year") year: Int? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonProperty("status") val status: Status? = null,
  @JsonProperty("ep_count") val epCount: Int? = null,
  @JsonProperty("rank") val rank: Int? = null,
  @JsonProperty("url") val url: String? = null,
  @JsonProperty("all_titles") val allTitles: List<String>? = null,
  @JsonProperty("endpoint_type") private val endpointType: Type,
) : StandardMediaObject(title, posterRaw, year, endpointType, ids) {
  @JsonIgnore
  override val type: Type = endpointType

  override fun toString(): String =
    "SearchTextResult(" +
      "title=$title, " +
      "poster=$posterRaw, " +
      "year=$year, " +
      "type=$type, " +
      "ids=$ids, " +
      "status=$status, " +
      "epCount=$epCount, " +
      "rank=$rank, " +
      "url=$url, " +
      "allTitles=$allTitles, " +
      "endpointType=$endpointType, " +
      "otherKeys=${otherKeys.keys}" +
      ")"
}
