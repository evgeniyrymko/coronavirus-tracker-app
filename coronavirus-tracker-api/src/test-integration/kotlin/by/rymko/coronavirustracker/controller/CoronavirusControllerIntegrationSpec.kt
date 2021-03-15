package by.rymko.coronavirustracker.controller

import by.rymko.coronavirustracker.CoronavirusTrackerApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.client.RestTemplate
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [CoronavirusTrackerApplication::class])
@AutoConfigureMockMvc
class CoronavirusControllerIntegrationSpec {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var mockServer: MockRestServiceServer

    @Before
    fun init() {
        mockServer = MockRestServiceServer.createServer(restTemplate)
    }

    @Test
    fun validCoronavirusDataProvided() {

        val validCoronavirusData = "{\"ID\":\"d84ed4e8-b761-48d3-911f-6b325c884f33\",\"Message\":\"\",\"Global\":{\"NewConfirmed\":193934,\"TotalConfirmed\":119719696,\"NewDeaths\":3472,\"TotalDeaths\":2651554,\"NewRecovered\":102497,\"TotalRecovered\":67880290,\"Date\":\"2021-03-15T08:13:57.186Z\"},\"Countries\":[{\"ID\":\"e8e0676b-2b15-4dc3-9729-f88767ba408d\",\"Country\":\"Afghanistan\",\"CountryCode\":\"AF\",\"Slug\":\"afghanistan\",\"NewConfirmed\":0,\"TotalConfirmed\":55985,\"NewDeaths\":0,\"TotalDeaths\":2457,\"NewRecovered\":0,\"TotalRecovered\":49477,\"Date\":\"2021-03-15T08:13:57.186Z\",\"Premium\":{}}],\"Date\":\"2021-03-15T08:13:57.186Z\"}"

        mockServer.expect(ExpectedCount.once(),
            requestTo(URI("https://api.covid19api.com/summary")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(validCoronavirusData)
            )

        mockMvc.perform(get("http://localhost:8081/stats").accept("application/json"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Afghanistan"))
            .andReturn()
    }
}
