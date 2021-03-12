package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.dto.CoronavirusDataDto
import by.rymko.coronavirustracker.dto.CountryStatsDto
import by.rymko.coronavirustracker.exception.CoronavirusDataException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

class DefaultCoronavirusDataProvider(
    private val restTemplate: RestTemplate,
    private val coronavirusDataProviderUrl: String,
    private val mapper: ObjectMapper
) : CoronavirusDataProvider {

    // return coronavirus data
    override fun getCoronavirusData(): List<CountryStatsDto> {

        // check null
        val coronavirusData = restTemplate.getForObject(coronavirusDataProviderUrl, String::class.java)
        if (coronavirusData == null || coronavirusData == "") {
            log.warning("Coronavirus Data received from $coronavirusDataProviderUrl is null or empty")
            throw CoronavirusDataException("Coronavirus Data received from $coronavirusDataProviderUrl is null or empty")
        } else {
            log.info("Coronavirus Data successfully received from $coronavirusDataProviderUrl")
        }

        return getCountryStatsDtoData(coronavirusData)
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
