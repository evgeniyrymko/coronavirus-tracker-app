package by.rymko.coronavirustracker.service

import cloud.folium.mpr.test.random.randomStr
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import io.kotest.matchers.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.springframework.web.client.RestTemplate

internal class CoronavirusDataProviderSpec : Spek({

    describe("coronavirus data provider") {

        val restTemplateMock = mock<RestTemplate>()

        val sut = CoronavirusDataProvider(restTemplateMock)

        beforeEachTest {
            reset(restTemplateMock)
        }

        on("coronavirus data provider") {

            val CORONAVIRUS_DATA_PROVIDER_URL = sut.CORONAVIRUS_DATA_PROVIDER_URL

            val randomStringObject = randomStr()

            whenever(restTemplateMock.getForObject(CORONAVIRUS_DATA_PROVIDER_URL, String::class.java))
                .thenReturn(randomStringObject)

            val result = sut.getCoronavirusData()

            it("returns random String object") {
                result shouldBe randomStringObject
            }
        }
    }
})
