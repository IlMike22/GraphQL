package com.example.graphql.domain

import com.example.graphql.domain.model.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: ICountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient.getCountries().sortedBy { it.name }
    }
}