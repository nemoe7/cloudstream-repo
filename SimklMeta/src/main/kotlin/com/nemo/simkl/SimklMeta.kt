package com.nemo.simkl

import android.content.SharedPreferences
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

open class SimklMeta(val sharedPreferences: SharedPreferences) : MainAPI() {

  override var name = "Simkl"
  override var hasQuickSearch = true
  override var hasMainPage = true

  override val supportedTypes = setOf(
    TvType.Movie,
    TvType.AnimeMovie,
    TvType.Cartoon,
    TvType.Anime,
    TvType.OVA,
    TvType.Documentary,
    TvType.AsianDrama,
  )
}
