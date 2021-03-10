package by.rymko.coronavirustracker.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CoronavirusDataProvider(val restTemplate: RestTemplate) {

    val CORONAVIRUS_DATA_PROVIDER_URL: String = "https://api.covid19api.com/summary"

    fun getCoronavirusData(): String? = restTemplate.getForObject(CORONAVIRUS_DATA_PROVIDER_URL, String::class.java)

}
