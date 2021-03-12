package by.rymko.coronavirustracker.configuration

import by.rymko.coronavirustracker.mapper.CountryStatsMapper
import by.rymko.coronavirustracker.mapper.DefaultCountryStatsMapper
import by.rymko.coronavirustracker.service.CoronavirusDataProvider
import by.rymko.coronavirustracker.service.CoronavirusService
import by.rymko.coronavirustracker.service.DefaultCoronavirusDataProvider
import by.rymko.coronavirustracker.service.DefaultCoronavirusService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.client.RestTemplate

@Configuration
class CoronavirusConfiguration {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate = restTemplateBuilder.build()

    @Bean
    fun coronavirusDataProvider(): CoronavirusDataProvider = DefaultCoronavirusDataProvider(
        restTemplate = restTemplate(restTemplateBuilder = RestTemplateBuilder()),
        coronavirusDataProviderUrl = env.getRequiredProperty("coronavirus.data.provider.url"),
        mapper = mapper()
    )

    @Bean
    fun mapper(): ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Bean
    fun countryStatsMapper(): CountryStatsMapper = DefaultCountryStatsMapper()

    @Bean
    fun coronavirusService(): CoronavirusService = DefaultCoronavirusService(
        coronavirusDataProvider = coronavirusDataProvider(),
        countryStatsMapper = countryStatsMapper()
    )
}
