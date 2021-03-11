package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.mapper.CountryStatsMapper
import by.rymko.coronavirustracker.response.CountryStatsResponse

class DefaultCoronavirusService(
    private val coronavirusDataProvider: CoronavirusDataProvider,
    private val countryStatsMapper: CountryStatsMapper
) : CoronavirusService {

    override fun getCountriesStats(): List<CountryStatsResponse> {

        return coronavirusDataProvider.getCoronavirusData().map { countryStatsMapper.mapToCountryStatsResponse(it) }
    }
}
