package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.dto.CoronavirusDataDto
import by.rymko.coronavirustracker.dto.CountryStatsDto
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

class DefaultCoronavirusDataProvider(
    private val restTemplate: RestTemplate,
    private val coronavirusDataProviderUrl: String
) : CoronavirusDataProvider {

    companion object {
        val log: Logger = Logger.getLogger(DefaultCoronavirusDataProvider::class.java.name)
    }

    private val mapper = jacksonObjectMapper()

    override fun getCoronavirusData(): List<CountryStatsDto> {

        val coronavirusData = restTemplate.getForObject(coronavirusDataProviderUrl, String::class.java)
        log.info("Coronavirus Data successfully received from $coronavirusDataProviderUrl")

        return getCountryStatsDtoData(coronavirusData)
    }

    private fun getCountryStatsDtoData(coronavirusData: String?): List<CountryStatsDto> {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val coronavirusDataDto: CoronavirusDataDto? = mapper.readValue(coronavirusData.toString())

        return coronavirusDataDto?.countriesStats!!
    }
}
