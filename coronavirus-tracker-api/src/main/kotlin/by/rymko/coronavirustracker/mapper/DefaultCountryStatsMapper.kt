package by.rymko.coronavirustracker.mapper

import by.rymko.coronavirustracker.dto.CountryStatsDto
import by.rymko.coronavirustracker.response.CountryStatsResponse

class DefaultCountryStatsMapper : CountryStatsMapper {

    override fun mapToCountryStatsResponse(countryStatsDto: CountryStatsDto): CountryStatsResponse {
        return CountryStatsResponse(
            name = countryStatsDto.name,
            newDeaths = countryStatsDto.newDeaths,
            totalDeaths = countryStatsDto.totalDeaths,
            newRecovered = countryStatsDto.newRecovered,
            totalRecovered = countryStatsDto.totalRecovered,
            date = countryStatsDto.date
        )
    }
}
