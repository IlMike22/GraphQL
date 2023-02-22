package com.example.graphql.domain

import com.example.graphql.domain.model.DetailedCountry

class GetCountryUseCase(
    private val countryClient: ICountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}