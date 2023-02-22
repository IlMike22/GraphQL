package com.example.graphql.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphql.domain.model.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountryState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCountry(country.code) },
                        onDismiss = onDismissCountryDialog
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryItem(
    modifier: Modifier = Modifier,
    country: SimpleCountry,
    onDismiss: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country.emojy, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = country.capital)
        }
    }
}