package by.rymko.coronavirustracker.response

data class CountryStatsResponse(
    val name: String,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
)
