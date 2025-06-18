package com.nemo.simkl

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class SimklMetaPlugin : Plugin() {
  override fun load(context: Context) {
    val sharedPrefs =
      context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)
    registerMainAPI(SimklMeta(sharedPrefs))
  }
}
