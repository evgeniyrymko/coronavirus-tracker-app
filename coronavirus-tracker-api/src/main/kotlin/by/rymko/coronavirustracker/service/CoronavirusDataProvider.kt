package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.dto.CountryStatsDto

interface CoronavirusDataProvider {

    fun getCoronavirusData(): List<CountryStatsDto>
}
