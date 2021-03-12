package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.exception.CoronavirusDataException
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

class DefaultCoronavirusDataProvider(
    private val restTemplate: RestTemplate,
    private val coronavirusDataProviderUrl: String
) : CoronavirusDataProvider {

    override fun getCoronavirusData(): String {

        val coronavirusData = restTemplate.getForObject(coronavirusDataProviderUrl, String::class.java)
        if (coronavirusData == null || coronavirusData == "") {
            log.warning("Coronavirus Data received from $coronavirusDataProviderUrl is null or empty")
            throw CoronavirusDataException("Coronavirus Data received from $coronavirusDataProviderUrl is null or empty")
        } else {
            log.info("Coronavirus Data successfully received from $coronavirusDataProviderUrl")
        }

        return coronavirusData
    }

    companion object {
        val log: Logger = Logger.getLogger(DefaultCoronavirusDataProvider::class.java.name)
    }
}
