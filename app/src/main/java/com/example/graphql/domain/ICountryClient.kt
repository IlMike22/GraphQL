package com.example.graphql.domain

import com.example.graphql.domain.model.DetailedCountry
import com.example.graphql.domain.model.SimpleCountry

interface ICountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}