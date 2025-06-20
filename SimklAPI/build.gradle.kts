import org.jetbrains.kotlin.konan.properties.Properties

android {
  namespace = "com.nemo.simkl"

  buildFeatures {
    buildConfig = true
  }

  defaultConfig {
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())
    buildConfigField("String", "SIMKL_API_KEY", "\"${properties.getProperty("SIMKL_API_KEY")}\"")
    buildConfigField("String", "SIMKL_API_SECRET", "\"${properties.getProperty("SIMKL_API_SECRET")}\"")
  }
}

cloudstream {
  isLibrary = true
}
