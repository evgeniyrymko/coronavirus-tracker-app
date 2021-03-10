package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.model.CoronavirusApiDto
import by.rymko.coronavirustracker.model.CountryStatsDto
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
class CoronavirusService(val coronavirusDataProvider: CoronavirusDataProvider) {

    fun getCountriesStats(): List<CountryStatsDto> {

        val coronavirusData = coronavirusDataProvider.getCoronavirusData()
        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val coronavirusApiDto: CoronavirusApiDto = mapper.readValue(coronavirusData!!)

        return coronavirusApiDto.countriesStats
    }

}
