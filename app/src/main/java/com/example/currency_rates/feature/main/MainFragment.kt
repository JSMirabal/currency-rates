package com.example.currency_rates.feature.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.currency_rates.R
import com.example.currency_rates.core.ui.BaseFragment
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * Created by jsmirabal on 4/18/2019.
 */
class MainFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun layoutId() = R.layout.fragment_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.successLiveData.observe(this, Observer {
            renderView(it)
        })
        viewModel.errorLiveData.observe(this, Observer {
            renderError(it)
        })
    }

    private fun renderError(it: Failure?) {
        hideProgress()
        Snackbar.make(this.requireView(), it?.toString() ?: "Some error", Snackbar.LENGTH_LONG).show()
    }

    private fun renderView(it: CurrencyView?) {
        hideProgress()
        text.text = it?.rates?.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showProgress()
            viewModel.loadRates(Params("2019-04-16", "2019-04-30"))
        }
    }
}