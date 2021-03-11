package by.rymko.coronavirustracker.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CoronavirusDataDto(
    @JsonProperty("Global") val globalStats: GlobalStatsDto?,
    @JsonProperty("Countries") val countriesStats: List<CountryStatsDto>?,
    @JsonProperty("Date") val date: String?
)
