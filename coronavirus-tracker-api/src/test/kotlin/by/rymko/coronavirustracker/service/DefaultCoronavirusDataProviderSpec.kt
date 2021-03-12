package by.rymko.coronavirustracker.service

import by.rymko.coronavirustracker.exception.CoronavirusDataException
import cloud.folium.mpr.test.random.randomPath
import cloud.folium.mpr.test.random.randomStr
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import io.kotest.matchers.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.assertThrows
import org.springframework.web.client.RestTemplate

internal class DefaultCoronavirusDataProviderSpec : Spek({

    describe("coronavirus data provider") {

        val restTemplateMock = mock<RestTemplate>()
        val validCoronavirusDataProviderUrl = "https://api.covid19api.com/summary"
        val invalidCoronavirusDataProviderUrl = randomPath()


        beforeEachTest {
            reset(restTemplateMock)
        }

        on("valid coronavirus data URL provided") {

            val sut = DefaultCoronavirusDataProvider(restTemplateMock, validCoronavirusDataProviderUrl)

            val randomStringObject = randomStr()

            whenever(restTemplateMock.getForObject(validCoronavirusDataProviderUrl, String::class.java))
                .thenReturn(randomStringObject)

            val result = sut.getCoronavirusData()

            it("returns String object") {
                result shouldBe randomStringObject
            }
        }

        on("invalid coronavirus data URL provided") {

            val sut = DefaultCoronavirusDataProvider(restTemplateMock, invalidCoronavirusDataProviderUrl)

            val emptyStringObject = ""

            whenever(restTemplateMock.getForObject(invalidCoronavirusDataProviderUrl, String::class.java))
                .thenReturn(emptyStringObject)

            it("CoronavirusDataException thrown") {
                assertThrows<CoronavirusDataException> {
                    sut.getCoronavirusData()
                }
            }
        }
    }
})
