package by.rymko.coronavirustracker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryStatsDto(
    @JsonProperty("Country") val name: String,
    @JsonProperty("CountryCode") val code: String,
    @JsonProperty("NewConfirmed") val newConfirmed: Int,
    @JsonProperty("TotalConfirmed") val totalConfirmed: Int,
    @JsonProperty("NewDeaths") val newDeaths: Int,
    @JsonProperty("TotalDeaths") val totalDeaths: Int,
    @JsonProperty("NewRecovered") val newRecovered: Int,
    @JsonProperty("TotalRecovered") val totalRecovered: Int,
    @JsonProperty("Date") val date: String
)