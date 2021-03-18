package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.dto.CoronavirusDataDto
import by.rymko.coronavirustracker.dto.CountryStatsDto
import by.rymko.coronavirustracker.exception.CoronavirusDataException
import by.rymko.coronavirustracker.mapper.CountryStatsMapper
import by.rymko.coronavirustracker.response.CountryStatsResponse
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.MismatchedInputException
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

        val coronavirusDataDto: CoronavirusDataDto
        try {
            coronavirusDataDto = mapper.readValue(coronavirusData)
            log.info("Coronavirus Data successfully mapped to CoronavirusDataDto")
        } catch (exception: MismatchedInputException) {
            log.warning("Coronavirus Data can not be mapped to CoronavirusDataDto, caught $exception")
            throw CoronavirusDataException("Coronavirus Data can not be mapped to CoronavirusDataDto, caught $exception")
        }
        catch (exception: JsonParseException) {
            log.warning("Coronavirus Data can not be mapped to CoronavirusDataDto")
            throw CoronavirusDataException("Coronavirus Data can not be mapped to CoronavirusDataDto")
        }

        return coronavirusDataDto.countriesStats!!
    }

    companion object {
        val log: Logger = Logger.getLogger(DefaultCoronavirusDataProvider::class.java.name)
    }
}
