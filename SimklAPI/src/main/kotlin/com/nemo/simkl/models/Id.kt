package com.nemo.simkl.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Represents a collection of platform-specific or standard identifiers for a media item.
 *
 * @property simkl The Simkl ID for the media item, if available.
 * @property slug The slug identifier for the media item.
 * @property hulu The Hulu ID for the media item, if available.
 * @property netflix The Netflix ID for the media item, if available.
 * @property mal The MyAnimeList ID for the media item, if available.
 * @property tvdb The TVDB ID for the media item, if available.
 * @property tmdb The TMDB ID for the media item, if available.
 * @property imdb The IMDb ID for the media item, if available.
 * @property anidb The AniDB ID for the media item, if available.
 * @property crunchyroll The Crunchyroll ID for the media item, if available.
 * @property anilist The AniList ID for the media item, if available.
 * @property kitsu The Kitsu ID for the media item, if available.
 * @property livechart The LiveChart ID for the media item, if available.
 * @property anisearch The AniSearch ID for the media item, if available.
 * @property animeplanet The AnimePlanet ID for the media item, if available.
 * @property _otherIds A map holding additional identifiers not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
data class Id(
  @JsonAlias("simkl_id") @JsonProperty("simkl") val simkl: Int? = null,
  @JsonProperty("slug") val slug: String? = null,
  @JsonProperty("hulu") val hulu: String? = null,
  @JsonProperty("netflix") val netflix: String? = null,
  @JsonProperty("mal") val mal: String? = null,
  @JsonProperty("tvdb") val tvdb: String? = null,
  @JsonProperty("tmdb") val tmdb: String? = null,
  @JsonProperty("imdb") val imdb: String? = null,
  @JsonProperty("anidb") val anidb: String? = null,
  @JsonProperty("crunchyroll") val crunchyroll: String? = null,
  @JsonProperty("anilist") val anilist: String? = null,
  @JsonProperty("kitsu") val kitsu: String? = null,
  @JsonProperty("livechart") val livechart: String? = null,
  @JsonProperty("anisearch") val anisearch: String? = null,
  @JsonProperty("animeplanet") val animeplanet: String? = null,

  @JsonIgnore private val _otherIds: MutableMap<String, String?> = mutableMapOf()
) {
  companion object {
    const val SIMKL = "simkl"
    const val SLUG = "slug"
    const val HULU = "hulu"
    const val NETFLIX = "netflix"
    const val MAL = "mal"
    const val TVDB = "tvdb"
    const val TMDB = "tmdb"
    const val IMDB = "imdb"
    const val ANIDB = "anidb"
    const val CRUNCHYROLL = "crunchyroll"
    const val ANILIST = "anilist"
    const val KITSU = "kitsu"
    const val LIVECHART = "livechart"
    const val ANISEARCH = "anisearch"
    const val ANIMEPLANET = "animeplanet"

    val TYPES = setOf(
      SIMKL,
      SLUG,
      HULU,
      NETFLIX,
      MAL,
      TVDB,
      TMDB,
      IMDB,
      ANIDB,
      CRUNCHYROLL,
      ANILIST,
      KITSU,
      LIVECHART,
      ANISEARCH,
      ANIMEPLANET
    )
  }

  @JsonAnySetter
  fun setOtherIds(key: String, value: String?) {
    _otherIds[key] = value
  }

  fun containsKey(key: String): Boolean =
    key in TYPES || _otherIds.containsKey(key)

  operator fun get(key: String): String? = when (key) {
    "simkl" -> simkl.toString()
    "slug" -> slug
    "hulu" -> hulu
    "netflix" -> netflix
    "mal" -> mal
    "tvdb" -> tvdb
    "tmdb" -> tmdb
    "imdb" -> imdb
    "anidb" -> anidb
    "crunchyroll" -> crunchyroll
    "anilist" -> anilist
    "kitsu" -> kitsu
    "livechart" -> livechart
    "anisearch" -> anisearch
    "animeplanet" -> animeplanet
    else -> _otherIds[key]
  }

  override fun toString(): String {
    val sb = StringBuilder()
    sb.append("Id(")
    TYPES.forEach { type ->
      val v = this[type]
      if (v != null) sb.append("$type=$v, ")
    }
    if (_otherIds.isNotEmpty()) sb.append("otherIds=${_otherIds.keys}, ")
    if (sb.endsWith(", ")) sb.delete(sb.length - 2, sb.length)
    sb.append(")")
    return sb.toString()
  }
}
