package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.response.CountryStatsResponse

interface CoronavirusService {

    fun getCountriesStats(): List<CountryStatsResponse>
}
