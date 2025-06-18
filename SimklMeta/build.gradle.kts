// use an integer for version numbers
version = 1

android {
  namespace = "com.nemo.simkl"

  buildFeatures {
    buildConfig = true
  }

  buildTypes{
    release {
      isMinifyEnabled = true
      proguardFiles("proguard-rules.pro")
    }
  }
}

cloudstream {
  language = "en"

  description = "Animes (SUB/DUB)"
  authors = listOf("nemoe7")

  /**
   * Status int as the following:
   * 0: Down
   * 1: Ok
   * 2: Slow
   * 3: Beta only
   * */
  status = 3 // will be 3 if unspecified
  tvTypes = listOf(
    "AnimeMovie",
    "Anime",
    "OVA",
  )
  iconUrl = "https://animepahe.ru/favicon-96x96.png"
}

dependencies {
  implementation(project(":SimklAPI"))
}
