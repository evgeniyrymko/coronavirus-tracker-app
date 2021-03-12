package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.dto.CoronavirusDataDto
import by.rymko.coronavirustracker.dto.CountryStatsDto
import by.rymko.coronavirustracker.exception.CoronavirusDataException
import by.rymko.coronavirustracker.mapper.CountryStatsMapper
import by.rymko.coronavirustracker.response.CountryStatsResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.logging.Logger

class DefaultCoronavirusService(
    private val coronavirusDataProvider: CoronavirusDataProvider,
    private val countryStatsMapper: CountryStatsMapper,
    private val mapper: ObjectMapper
) : CoronavirusService {

    override fun getCountriesStats(): List<CountryStatsResponse> {

        val coronavirusData = coronavirusDataProvider.getCoronavirusData()
        val coronavirusDataDto = getCountryStatsDtoData(coronavirusData)

        return coronavirusDataDto.map { countryStatsMapper.mapToCountryStatsResponse(it) }
    }

    private fun getCountryStatsDtoData(coronavirusData: String): List<CountryStatsDto> {

        // add exception handling
        val coronavirusDataDto = mapper.readValue<CoronavirusDataDto>(coronavirusData)
        if (coronavirusDataDto == null) {

            log.warning("Coronavirus Data can not be mapped to CoronavirusDataDto")
            throw CoronavirusDataException("Coronavirus Data can not be mapped to CoronavirusDataDto")
        } else {
            log.info("Coronavirus Data successfully mapped to CoronavirusDataDto")
        }

        return coronavirusDataDto.countriesStats!!
    }

    companion object {
        val log: Logger = Logger.getLogger(DefaultCoronavirusDataProvider::class.java.name)
    }
}
