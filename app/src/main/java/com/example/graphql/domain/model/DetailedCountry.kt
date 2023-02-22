package com.example.graphql.domain.model

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val languages: List<String>,
    val currency: String,
    val continent: String
)
