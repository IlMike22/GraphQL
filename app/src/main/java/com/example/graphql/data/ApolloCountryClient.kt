package com.example.graphql.data

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphql.data.mapper.toDetailedCountry
import com.example.graphql.data.mapper.toSimpleCountry
import com.example.graphql.domain.ICountryClient
import com.example.graphql.domain.model.DetailedCountry
import com.example.graphql.domain.model.SimpleCountry

class ApolloCountryClient(
    private val appoloClient: ApolloClient
) : ICountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return appoloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return appoloClient.query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}