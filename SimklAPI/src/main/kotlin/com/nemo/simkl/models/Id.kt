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
 * @property otherIds A map holding additional identifiers not explicitly defined as part of the standard schema.
 *   These can provide additional metadata that extends beyond what is captured in predefined fields.
 */
data class Id(
  @JsonAlias("simkl_id") @JsonProperty("simkl") val simkl: String? = null,
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
  @JsonIgnore private val otherIds: MutableMap<String, String?> = mutableMapOf()
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
    otherIds[key] = value
  }

  fun getOtherIds(): Map<String, Any?> = otherIds

  fun getId(key: String): String? = when (key) {
    "simkl" -> simkl
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
    else -> otherIds[key]
  }

  override fun toString(): String {
    val sb = StringBuilder()
    sb.append("Id(")
    if (simkl != null) sb.append("simkl=$simkl, ")
    if (slug != null) sb.append("slug=$slug, ")
    if (hulu != null) sb.append("hulu=$hulu, ")
    if (netflix != null) sb.append("netflix=$netflix, ")
    if (mal != null) sb.append("mal=$mal, ")
    if (tvdb != null) sb.append("tvdb=$tvdb, ")
    if (tmdb != null) sb.append("tmdb=$tmdb, ")
    if (imdb != null) sb.append("imdb=$imdb, ")
    if (anidb != null) sb.append("anidb=$anidb, ")
    if (crunchyroll != null) sb.append("crunchyroll=$crunchyroll, ")
    if (anilist != null) sb.append("anilist=$anilist, ")
    if (kitsu != null) sb.append("kitsu=$kitsu, ")
    if (livechart != null) sb.append("livechart=$livechart, ")
    if (anisearch != null) sb.append("anisearch=$anisearch, ")
    if (animeplanet != null) sb.append("animeplanet=$animeplanet, ")
    if (otherIds.isNotEmpty()) sb.append("otherIds=${otherIds.keys}, ")
    sb.delete(sb.length - 2, sb.length)
    sb.append(")")
    return sb.toString()
  }

}
