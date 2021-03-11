package by.rymko.coronavirustracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoronavirusTrackerApplication

fun main(args: Array<String>) {
    runApplication<CoronavirusTrackerApplication>(*args)
}
