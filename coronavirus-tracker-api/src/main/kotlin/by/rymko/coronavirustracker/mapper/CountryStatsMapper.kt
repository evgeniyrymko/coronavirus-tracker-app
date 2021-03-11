package by.rymko.coronavirustracker.mapper

import by.rymko.coronavirustracker.dto.CountryStatsDto
import by.rymko.coronavirustracker.response.CountryStatsResponse

interface CountryStatsMapper {

    fun mapToCountryStatsResponse(countryStatsDto: CountryStatsDto): CountryStatsResponse
}
