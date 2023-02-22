package com.example.graphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphql.domain.GetCountriesUseCase
import com.example.graphql.domain.GetCountryUseCase
import com.example.graphql.domain.model.DetailedCountry
import com.example.graphql.domain.model.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountryState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update { it.copy(selectedCountry = null) }
    }

    data class CountryState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}