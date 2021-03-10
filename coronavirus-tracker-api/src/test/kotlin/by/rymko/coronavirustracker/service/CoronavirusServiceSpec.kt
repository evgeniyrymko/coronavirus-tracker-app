package by.rymko.coronavirustracker.service

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class CoronavirusServiceSpec : Spek({

    describe("coronavirus service") {

        val coronavirusDataProviderMock = mock<CoronavirusDataProvider>()

        val sut = CoronavirusService(coronavirusDataProviderMock)

        beforeEachTest {
            reset(coronavirusDataProviderMock)
        }

        on("correct coronavirus data with one country") {

            val coronavirusDataWithOneCountry =
                "{\"Global\":{\"NewConfirmed\":315513,\"TotalConfirmed\":117136374,\"NewDeaths\":6576,\"TotalDeaths\":2600510,\"NewRecovered\":285051,\"TotalRecovered\":66463117,\"Date\":\"2021-03-10T05:37:46.876Z\"},\"Countries\":[{\"ID\":\"150640d7-6abd-4216-8d1b-0903ccf7c11d\",\"Country\":\"Afghanistan\",\"CountryCode\":\"AF\",\"Slug\":\"afghanistan\",\"NewConfirmed\":0,\"TotalConfirmed\":55876,\"NewDeaths\":0,\"TotalDeaths\":2451,\"NewRecovered\":7,\"TotalRecovered\":49409,\"Date\":\"2021-03-10T05:37:46.876Z\",\"Premium\":{}}],\"Date\":\"2021-03-10T05:37:46.876Z\"}"

            whenever(coronavirusDataProviderMock.getCoronavirusData())
                .thenReturn(coronavirusDataWithOneCountry)

            val result = sut.getCountriesStats()

            println(result[0])

            it("returns list with one element") {
                result.shouldNotBeEmpty()
                result.size.shouldBe(1)
            }
        }
    }
})
