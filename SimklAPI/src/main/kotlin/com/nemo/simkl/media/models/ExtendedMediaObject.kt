package com.nemo.simkl.media.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Status
import com.nemo.simkl.search.models.Type

/**
 * Represents a media object with extended properties.
 *
 * @property title The title of the media object.
 * @property poster The poster image URL of the media object.
 * @property year The year of the media object.
 * @property type The type of the media object.
 * @property ids The IDs of the media object.
 * @property usersRecommendations A list of media objects that have been recommended by users.
 * @property trailers A list of trailers for the media object.
 * @property ratings A map of ratings for the media object.
 * @property network The network of the media object.
 * @property status The status of the media item, which can be null if unavailable.
 * @property totalEpisodes The total number of episodes in the media item, which might be null if unavailable.
 * @property country The country of the media object.
 * @property genres A list of genres for the media object.
 * @property overview The overview of the media object, nullable for unknown or unavailable cases.
 * @property certification The certification of the media object, nullable for unknown or unavailable cases.
 * @property runtime The runtime of the media object, nullable for unknown or unavailable cases.
 * @property airs A map holding the airing schedule of the media object, nullable for unknown or unavailable cases.
 * @property lastAiredRaw The raw date string representing the last air date of the media object, nullable for unknown or unavailable cases.
 * @property firstAiredRaw The raw date string representing the first air date of the media object, nullable for unknown or unavailable cases.
 * @property fanart The fanart image URL of the media object, nullable for unknown or unavailable cases.
 * @property rank The rank of the media object, nullable for unknown or unavailable cases.
 * @property yearStartEnd The year start and end of the media object, nullable for unknown or unavailable cases.
 */
class ExtendedMediaObject(
  @JsonProperty("title") title: String? = null,
  @JsonProperty("poster") posterRaw: String? = null,
  @JsonProperty("year") year: Int? = null,
  @JsonProperty("type") type: Type? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonProperty("other_keys") _otherKeys: MutableMap<String, Any?> = mutableMapOf(),
  @JsonProperty("users_recommendations") val usersRecommendations: List<StandardMediaObject>? = null,
  @JsonProperty("trailers") val trailers: List<Any>? = null,
  @JsonProperty("ratings") val ratings: LinkedHashMap<String, Any?>? = null,
  @JsonProperty("network") val network: String? = null,
  @JsonProperty("status") val status: Status? = null,
  @JsonProperty("total_episodes") val totalEpisodes: Int? = null,
  @JsonProperty("country") val country: String? = null,
  @JsonProperty("genres") val genres: List<String>? = null,
  @JsonProperty("overview") val overview: String? = null,
  @JsonProperty("certification") val certification: String? = null,
  @JsonProperty("runtime") val runtime: String? = null,
  @JsonProperty("airs") val airs: LinkedHashMap<String, Any?>? = null,
  @JsonProperty("last_aired") val lastAiredRaw: String? = null,
  @JsonProperty("first_aired") val firstAiredRaw: String? = null,
  @JsonProperty("fanart") val fanart: String? = null,
  @JsonProperty("rank") val rank: Int? = null,
  @JsonProperty("year_start_end") val yearStartEnd: String? = null,
): StandardMediaObject(title, posterRaw, year, type, ids) {

  val escapedOverview: String
    get() {
      if (overview == null) return ""
      return buildString {
        for (c in overview) {
          append(
            when (c) {
              '\r' -> "\\r"
              '\n' -> "\\n"
              '\t' -> "\\t"
              '\b' -> "\\b"
              '\u000C' -> "\\f"
              '\u0007' -> "\\a"
              '\u0000' -> "\\0"
              else -> c
            }
          )
        }
      }
    }

  fun copy(
    title: String? = this.title,
    posterRaw: String? = this.posterRaw,
    year: Int? = this.year,
    type: Type? = this.type,
    ids: Id = this.ids,
    usersRecommendations: List<StandardMediaObject>? = this.usersRecommendations,
    trailers: List<Any>? = this.trailers,
    ratings: LinkedHashMap<String, Any?>? = this.ratings,
    network: String? = this.network,
    status: Status? = this.status,
    totalEpisodes: Int? = this.totalEpisodes,
    country: String? = this.country,
    genres: List<String>? = this.genres,
    overview: String? = this.overview,
    certification: String? = this.certification,
    runtime: String? = this.runtime,
    airs: LinkedHashMap<String, Any?>? = this.airs,
    lastAiredRaw: String? = this.lastAiredRaw,
    firstAiredRaw: String? = this.firstAiredRaw,
    fanart: String? = this.fanart,
    rank: Int? = this.rank,
    yearStartEnd: String? = this.yearStartEnd,
    _otherKeys: MutableMap<String, Any?> = this._otherKeys
  ) = ExtendedMediaObject(
    title = title,
    posterRaw = posterRaw,
    year = year,
    type = type,
    ids = ids,
    usersRecommendations = usersRecommendations,
    trailers = trailers,
    ratings = ratings,
    network = network,
    status = status,
    totalEpisodes = totalEpisodes,
    country = country,
    genres = genres,
    overview = overview,
    certification = certification,
    runtime = runtime,
    airs = airs,
    lastAiredRaw = lastAiredRaw,
    firstAiredRaw = firstAiredRaw,
    fanart = fanart,
    rank = rank,
    yearStartEnd = yearStartEnd,
    _otherKeys = _otherKeys
  )

  override fun toString(): String =
    "ExtendedMediaObject(" +
      "title=$title, " +
      "poster=$posterRaw, " +
      "year=$year, " +
      "type=$type, " +
      "ids=$ids, " +
      "usersRecommendations(ids)=[${usersRecommendations?.map { it.ids.simkl }?.joinToString(",") ?: "null"}], " +
      "trailers=$trailers, " +
      "ratings=$ratings, " +
      "network=$network, " +
      "status=$status, " +
      "totalEpisodes=$totalEpisodes, " +
      "country=$country, " +
      "genres=$genres, " +
      "overview=$escapedOverview, " +
      "certification=$certification, " +
      "runtime=$runtime, " +
      "airs=$airs, " +
      "lastAiredRaw=$lastAiredRaw, " +
      "firstAiredRaw=$firstAiredRaw, " +
      "fanart=$fanart, " +
      "rank=$rank, " +
      "yearStartEnd=\"$yearStartEnd\", " +
      "otherKeys=${otherKeys.keys}" +
      ")"
}
