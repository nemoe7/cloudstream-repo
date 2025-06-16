package com.nemo.vidsrc

import android.content.Context
import com.lagradost.cloudstream3.extractors.VidSrcExtractor
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class VidSrcPlugin : Plugin() {
  override fun load(context: Context) {
    val sharedPrefs =
      context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)
    registerMainAPI(VidSrcAPI(sharedPrefs))
    registerExtractorAPI(VidSrcExtractor())
  }
}
