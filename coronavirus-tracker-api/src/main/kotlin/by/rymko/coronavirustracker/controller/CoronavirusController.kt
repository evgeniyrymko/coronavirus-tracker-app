package by.rymko.coronavirustracker.controller

import by.rymko.coronavirustracker.response.CountryStatsResponse
import by.rymko.coronavirustracker.service.CoronavirusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoronavirusController(private val coronavirusService: CoronavirusService) {

    @GetMapping("/stats")
    fun getCountriesStats(): List<CountryStatsResponse> = coronavirusService.getCountriesStats()
}
