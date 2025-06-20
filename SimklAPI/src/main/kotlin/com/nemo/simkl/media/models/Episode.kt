package com.nemo.simkl.media.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.nemo.simkl.SimklAPI.IMAGE_BASE_URL
import com.nemo.simkl.SimklAPI.toDateTime
import com.nemo.simkl.models.Id
import com.nemo.simkl.models.ImageExtension
import com.nemo.simkl.models.PosterSuffix
import com.nemo.simkl.models.StandardMediaObject
import com.nemo.simkl.search.models.Type
import java.time.LocalDateTime

/**
 * Represents an episode of a media item.
 *
 * @property title The title of the episode.
 * @property posterRaw The raw URL pointing to the poster image, if available.
 * @property year The release year of the episode, nullable for unknown or unavailable cases.
 * @property type The type of the episode, which might be null if unavailable.
 * @property ids A collection of platform-specific or standard identifiers represented by the `Id` class.
 * @property date The date of the episode, nullable for unknown or unavailable cases.
 * @property aired Whether the episode has aired or not.
 * @property episode The episode number.
 * @property description The description of the episode, nullable for unknown or unavailable cases.
 */
class Episode(
  @JsonProperty("title") title: String? = null,
  @JsonAlias("img") @JsonProperty("poster") posterRaw: String? = null,
  @JsonProperty("year") private val _year: Int? = null,
  @JsonProperty("type") type: Type? = null,
  @JsonProperty("ids") ids: Id = Id(),
  @JsonIgnore @JsonProperty("date") private val dateString: String? = null,
  @JsonProperty("aired") val aired: Boolean,
  @JsonProperty("episode") val episode: Int,
  @JsonProperty("description") val description: String? = null,
) : StandardMediaObject(title, posterRaw, _year, type, ids) {
  @get:JsonProperty("date")
  val date: LocalDateTime?
    @RequiresApi(Build.VERSION_CODES.O) get() = dateString?.toDateTime()

  override val year: Int?
    @RequiresApi(Build.VERSION_CODES.O) get() = date?.year ?: _year

  fun image(
    suffix: EpisodeImageSuffix = EpisodeImageSuffix.W,
    extension: ImageExtension = ImageExtension.JPG
  ): String = "$IMAGE_BASE_URL/$posterRaw$suffix$extension"

  /**
   * DO NOT USE THIS!
   *
   * Use `EpisodeMediaObject.image(EpisodeImageSuffix, ImageExtension)` for EpisodeMediaObjects!
   *
   * @return An empty string
   */
  override fun poster(suffix: PosterSuffix, extension: ImageExtension): String = ""

  @RequiresApi(Build.VERSION_CODES.O)
  override fun toString(): String =
    "Episode(" +
      "title=$title, " +
      "poster=$posterRaw, " +
      "year=$year, " +
      "type=$type, " +
      "ids=$ids, " +
      "date=$date, " +
      "aired=$aired, " +
      "episode=$episode, " +
      "description=$description, " +
      "otherKeys=${otherKeys.keys}" +
      ")"
}
