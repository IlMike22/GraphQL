package com.example.graphql.di

import com.apollographql.apollo3.ApolloClient
import com.example.graphql.data.ApolloCountryClient
import com.example.graphql.domain.GetCountriesUseCase
import com.example.graphql.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): ApolloCountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(apolloCountryClient: ApolloCountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(apolloCountryClient)
    }

    @Provides
    @Singleton
    fun provideCountryUseCase(apolloCountryClient: ApolloCountryClient): GetCountryUseCase {
        return GetCountryUseCase(apolloCountryClient)
    }
}