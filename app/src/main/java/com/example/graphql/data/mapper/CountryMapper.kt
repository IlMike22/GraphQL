package com.example.graphql.data.mapper

import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphql.domain.model.DetailedCountry
import com.example.graphql.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emojy = emoji,
        capital = capital ?: "No capital"
    )
}