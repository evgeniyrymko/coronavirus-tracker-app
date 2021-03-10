package by.rymko.coronavirustracker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CoronavirusApiDto(
    @JsonProperty("Global") val globalStats: GlobalStatsDto,
    @JsonProperty("Countries") val countriesStats: List<CountryStatsDto>,
    @JsonProperty("Date") val date: String
)
