// use an integer for version numbers
version = 1

android {
  namespace = "com.nemo.vidsrc"

  buildFeatures {
    buildConfig = true
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles("proguard-rules.pro")
    }
  }
}

cloudstream {
  language = "en"
  // All of these properties are optional, you can safely remove them

  description = "VidSrc"
  authors = listOf("nemoe7")

  /**
   * Status int as the following:
   * 0: Down
   * 1: Ok
   * 2: Slow
   * 3: Beta only
   * */
  status = 1 // will be 3 if unspecified
  tvTypes = listOf(
    "AsianDrama",
    "TvSeries",
    "Anime",
    "Movie",
    "Cartoon",
    "AnimeMovie"
  )
  iconUrl = "https://vidsrc.me/template/vidsrc-ico.png"
  // requiresResources = true
}

dependencies {
}
