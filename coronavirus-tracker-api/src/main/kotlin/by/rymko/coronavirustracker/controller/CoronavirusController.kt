package by.rymko.coronavirustracker.controller

import by.rymko.coronavirustracker.model.CountryStatsDto
import by.rymko.coronavirustracker.service.CoronavirusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoronavirusController(val coronavirusService: CoronavirusService) {

    @GetMapping("/stats")
    fun getCountriesStats(): List<CountryStatsDto> = coronavirusService.getCountriesStats()
}
