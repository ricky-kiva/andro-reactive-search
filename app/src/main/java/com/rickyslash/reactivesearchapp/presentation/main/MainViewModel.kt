package com.rickyslash.reactivesearchapp.presentation.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rickyslash.reactivesearchapp.R
import com.rickyslash.reactivesearchapp.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(context: Context): ViewModel() {

    private val accessToken = context.getString(R.string.geo_api_key)
    val queryChannel = MutableStateFlow("")

    val searchResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .mapLatest {
            ApiConfig.provideApiService().getCountry(it, accessToken).features
        }
        .asLiveData()

}