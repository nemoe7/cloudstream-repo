package com.nemo.simkl.sync.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.nemo.simkl.SimklAPI
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
suspend fun SimklAPI.getAllItems(
  type: Type? = null,
  status: Status? = null,
  dateFrom: LocalDateTime? = null,
  extended: Extended? = null,
  episodeWatchedAt: Boolean = false,
  memos: Boolean = false
): Response {
  val params = buildMap {
    dateFrom?.let { put("date_from", it.toDateTimeString()) }
    extended?.let { put("extended", it.value) }
    if (episodeWatchedAt) put("episode_watched_at", "yes")
    if (memos) put("memos", "yes")
  }
  return get(
    url = "/sync/all-items" + "/${type ?: ""}" + "/${status ?: ""}", params = params
  ).parsed<Response>()
}

// Possible values: shows, movies, anime.
enum class Type(@get:JsonValue val value: String) {
  @JsonProperty("movies")
  MOVIES("movies"),

  @JsonProperty("shows")
  SHOWS("shows"),

  @JsonProperty("anime")
  ANIME("anime");

  override fun toString(): String = value
}

// Possible values: watching, plantowatch, hold, completed, dropped.
enum class Status(@get:JsonValue val value: String) {
  @JsonProperty("watching")
  WATCHING("watching"),

  @Suppress("SpellCheckingInspection")
  @JsonProperty("plantowatch")
  PLANTOWATCH("plantowatch"),

  @JsonProperty("hold")
  HOLD("hold"),

  @JsonProperty("completed")
  COMPLETED("completed"),

  @JsonProperty("dropped")
  DROPPED("dropped");

  override fun toString(): String = value
}

// Possible values: full, full_anime_seasons, simkl_ids_only, ids_only.
enum class Extended(@get:JsonValue val value: String) {
  @JsonProperty("full")
  FULL("full"),

  @JsonProperty("full_anime_seasons")
  FULL_ANIME_SEASONS("full_anime_seasons"),

  @JsonProperty("simkl_ids_only")
  SIMKL_IDS_ONLY("simkl_ids_only"),

  @JsonProperty("ids_only")
  IDS_ONLY("ids_only");

  override fun toString(): String = value
}

// Possible values: yes.
enum class EpisodeWatchedAt(@get:JsonValue val value: String) {
  @JsonProperty("yes")
  YES("yes");

  override fun toString(): String = value
}

// Possible values: yes.
enum class Memos(@get:JsonValue val value: String) {
  @JsonProperty("yes")
  YES("yes");

  override fun toString(): String = value
}

@Suppress("SpellCheckingInspection")
data class Response(
  @JsonProperty("shows") val shows: List<Show> = listOf(),
  @JsonProperty("movies") val movies: List<Movie> = listOf(),
  @JsonProperty("anime") val anime: List<Anime> = listOf()
) {
  data class Show(
    @JsonProperty("added_to_watchlist_at") val addedToWatchlistAt: String? = null,
    @JsonProperty("last_watched_at") val lastWatchedAt: String? = null,
    @JsonProperty("user_rated_at") val userRatedAt: String? = null,
    @JsonProperty("user_rating") val userRating: Int? = null,
    @JsonProperty("status") val status: String? = null,
    @JsonProperty("last_watched") val lastWatched: String? = null,
    @JsonProperty("next_to_watch") val nextToWatch: String? = null,
    @JsonProperty("watched_episodes_count") val watchedEpisodesCount: Int? = null,
    @JsonProperty("total_episodes_count") val totalEpisodesCount: Int? = null,
    @JsonProperty("not_aired_episodes_count") val notAiredEpisodesCount: Int? = null,
    @JsonProperty("show") val show: Show = Show(),
    @JsonProperty("seasons") val seasons: List<Season>? = null
  ) {
    data class Show(
      @JsonProperty("title") val title: String? = null,
      @JsonProperty("poster") val poster: String? = null,
      @JsonProperty("year") val year: Int? = null,
      @JsonProperty("runtime") val runtime: Int? = null,
      @JsonProperty("ids") val ids: Ids = Ids()
    ) {
      data class Ids(
        @JsonProperty("simkl") val simkl: Int? = null,
        @JsonProperty("slug") val slug: String? = null,
        @JsonProperty("offen") val offen: String? = null,
        @JsonProperty("imdb") val imdb: String? = null,
        @JsonProperty("zap2it") val zap2it: String? = null,
        @JsonProperty("tvdbslug") val tvdbslug: String? = null,
        @JsonProperty("fb") val fb: String? = null,
        @JsonProperty("instagram") val instagram: String? = null,
        @JsonProperty("tw") val tw: String? = null,
        @JsonProperty("tmdb") val tmdb: String? = null,
        @JsonProperty("trakttvslug") val trakttvslug: String? = null,
        @JsonProperty("jwtv") val jwtv: String? = null,
        @JsonProperty("jwslug") val jwslug: String? = null,
        @JsonProperty("tvdb") val tvdb: String? = null
      )
    }

    data class Season(
      @JsonProperty("number") val number: Int? = null,
      @JsonProperty("episodes") val episodes: List<Episode> = listOf()
    ) {
      data class Episode(
        @JsonProperty("number") val number: Int? = null,
        @JsonProperty("tvdb") val tvdb: Tvdb = Tvdb()
      ) {
        data class Tvdb(
          @JsonProperty("season") val season: Int? = null,
          @JsonProperty("episode") val episode: Int? = null
        )
      }
    }
  }

  data class Movie(
    @JsonProperty("added_to_watchlist_at") val addedToWatchlistAt: String? = null,
    @JsonProperty("last_watched_at") val lastWatchedAt: String? = null,
    @JsonProperty("user_rated_at") val userRatedAt: String? = null,
    @JsonProperty("user_rating") val userRating: Int? = null,
    @JsonProperty("status") val status: String? = null,
    @JsonProperty("watched_episodes_count") val watchedEpisodesCount: Int? = null,
    @JsonProperty("total_episodes_count") val totalEpisodesCount: Int? = null,
    @JsonProperty("not_aired_episodes_count") val notAiredEpisodesCount: Int? = null,
    @JsonProperty("movie") val movie: Movie = Movie()
  ) {
    data class Movie(
      @JsonProperty("title") val title: String? = null,
      @JsonProperty("poster") val poster: String? = null,
      @JsonProperty("year") val year: Int? = null,
      @JsonProperty("runtime") val runtime: Int? = null,
      @JsonProperty("ids") val ids: Ids = Ids()
    ) {
      data class Ids(
        @JsonProperty("simkl") val simkl: Int? = null,
        @JsonProperty("slug") val slug: String? = null,
        @JsonProperty("fb") val fb: String? = null,
        @JsonProperty("tvdbmslug") val tvdbmslug: String? = null,
        @JsonProperty("imdb") val imdb: String? = null,
        @JsonProperty("letterslug") val letterslug: String? = null,
        @JsonProperty("jwslug") val jwslug: String? = null,
        @JsonProperty("jwm") val jwm: String? = null,
        @JsonProperty("traktmslug") val traktmslug: String? = null,
        @JsonProperty("tmdb") val tmdb: String? = null,
        @JsonProperty("offen") val offen: String? = null,
        @JsonProperty("instagram") val instagram: String? = null,
        @JsonProperty("tw") val tw: String? = null
      )
    }
  }

  data class Anime(
    @JsonProperty("added_to_watchlist_at") val addedToWatchlistAt: String? = null,
    @JsonProperty("last_watched_at") val lastWatchedAt: String? = null,
    @JsonProperty("user_rated_at") val userRatedAt: String? = null,
    @JsonProperty("user_rating") val userRating: Int? = null,
    @JsonProperty("status") val status: String? = null,
    @JsonProperty("last_watched") val lastWatched: String? = null,
    @JsonProperty("next_to_watch") val nextToWatch: String? = null,
    @JsonProperty("watched_episodes_count") val watchedEpisodesCount: Int? = null,
    @JsonProperty("total_episodes_count") val totalEpisodesCount: Int? = null,
    @JsonProperty("not_aired_episodes_count") val notAiredEpisodesCount: Int? = null,
    @JsonProperty("show") val show: Show = Show(),
    @JsonProperty("anime_type") val animeType: String? = null,
    @JsonProperty("mapped_tvdb_seasons") val mappedTvdbSeasons: List<Int>? = null,
    @JsonProperty("seasons") val seasons: List<Season>? = null
  ) {
    data class Show(
      @JsonProperty("title") val title: String? = null,
      @JsonProperty("poster") val poster: String? = null,
      @JsonProperty("year") val year: Int? = null,
      @JsonProperty("runtime") val runtime: Int? = null,
      @JsonProperty("ids") val ids: Ids = Ids()
    ) {
      data class Ids(
        @JsonProperty("simkl") val simkl: Int? = null,
        @JsonProperty("slug") val slug: String? = null,
        @JsonProperty("ann") val ann: String? = null,
        @JsonProperty("mal") val mal: String? = null,
        @JsonProperty("offjp") val offjp: String? = null,
        @JsonProperty("wikien") val wikien: String? = null,
        @JsonProperty("wikijp") val wikijp: String? = null,
        @JsonProperty("allcin") val allcin: String? = null,
        @JsonProperty("imdb") val imdb: String? = null,
        @JsonProperty("tmdb") val tmdb: String? = null,
        @JsonProperty("tw") val tw: String? = null,
        @JsonProperty("tvdbslug") val tvdbslug: String? = null,
        @JsonProperty("anilist") val anilist: String? = null,
        @JsonProperty("livechart") val livechart: String? = null,
        @JsonProperty("anisearch") val anisearch: String? = null,
        @JsonProperty("kitsu") val kitsu: String? = null,
        @JsonProperty("animeplanet") val animeplanet: String? = null,
        @JsonProperty("trakttvslug") val trakttvslug: String? = null,
        @JsonProperty("jwtv") val jwtv: String? = null,
        @JsonProperty("jwslug") val jwslug: String? = null,
        @JsonProperty("anidb") val anidb: String? = null,
        @JsonProperty("offen") val offen: String? = null,
        @JsonProperty("tvdb") val tvdb: String? = null,
        @JsonProperty("crunchyroll") val crunchyroll: String? = null,
        @JsonProperty("instagram") val instagram: String? = null,
        @JsonProperty("zap2it") val zap2it: String? = null,
        @JsonProperty("anfo") val anfo: String? = null,
        @JsonProperty("vndb") val vndb: String? = null,
        @JsonProperty("fb") val fb: String? = null,
        @JsonProperty("letterslug") val letterslug: String? = null,
        @JsonProperty("jwm") val jwm: String? = null,
        @JsonProperty("traktmslug") val traktmslug: String? = null,
        @JsonProperty("tvdbmslug") val tvdbmslug: String? = null
      )
    }

    data class Season(
      @JsonProperty("number") val number: Int? = null,
      @JsonProperty("episodes") val episodes: List<Episode> = listOf()
    ) {
      data class Episode(
        @JsonProperty("number") val number: Int? = null,
        @JsonProperty("tvdb") val tvdb: Tvdb = Tvdb()
      ) {
        data class Tvdb(
          @JsonProperty("season") val season: Int? = null,
          @JsonProperty("episode") val episode: Int? = null
        )
      }
    }
  }
}
