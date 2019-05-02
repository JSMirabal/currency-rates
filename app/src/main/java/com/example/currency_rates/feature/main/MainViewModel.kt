package com.example.currency_rates.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.example.domain.entity.CurrencyHistory
import com.example.domain.usecase.FetchCurrencyHistory
import javax.inject.Inject

/**
 * Created by jsmirabal on 5/1/2019.
 */
class MainViewModel
@Inject constructor(private val fetchCurrencyHistory: FetchCurrencyHistory) : ViewModel() {

    val successLiveData = MutableLiveData<CurrencyView>()
    val errorLiveData = MutableLiveData<Failure>()

    override fun onCleared() {
        super.onCleared()
        fetchCurrencyHistory.cancel()
    }

    fun loadRates(params: Params) {
        fetchCurrencyHistory(params) { it.either(::handleFailure, ::handleSuccess) }
    }

    private fun handleFailure(failure: Failure) {
        errorLiveData.value = failure
    }

    private fun handleSuccess(result: CurrencyHistory) {
        successLiveData.value = CurrencyView(
            result.rates
        )
    }
}