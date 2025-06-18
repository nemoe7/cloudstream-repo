package com.nemo.simkl.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore

data class StandardMediaObject(
  @JsonProperty("title") val title: String? = null,
  @JsonProperty("poster") val poster: String? = null,
  @JsonProperty("year") val year: Int? = null,
  @JsonProperty("runtime") val runtime: Int? = null,
  @JsonProperty("ids") val ids: Ids = Ids(),

  @JsonIgnore private val otherProperties: MutableMap<String, Any?> = mutableMapOf()
) {
  @JsonAnySetter
  fun setOtherProperty(key: String, value: Any?) {
    otherProperties[key] = value
  }

  fun getOtherProperties(): Map<String, Any?> = otherProperties
}

data class Ids(
  @JsonProperty("ids")
  val ids: Map<String, String?> = emptyMap()
)
