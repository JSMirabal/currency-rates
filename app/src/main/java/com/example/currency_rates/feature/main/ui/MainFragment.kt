package com.example.currency_rates.feature.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.currency_rates.R
import com.example.currency_rates.core.ui.BaseFragment
import com.example.currency_rates.feature.main.MainViewModel
import com.example.currency_rates.feature.main.model.CurrencyModel
import com.example.domain.core.Failure
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * Created by jsmirabal on 4/18/2019.
 */
class MainFragment: BaseFragment() {

    private val TAG: String = MainFragment::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: MainAdapter

    override fun layoutId() = R.layout.fragment_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        adapter = MainAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.successLiveData.observe(viewLifecycleOwner, Observer {
            renderView(it)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            renderError(it)
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = this.adapter
        viewPager.registerOnPageChangeCallback(onPageChanged())
        if (savedInstanceState == null) {
            showProgress()
            //viewModel.loadRates(Params("2019-04-16", "2019-04-30"))
        }
    }

    private fun onPageChanged() = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.d(TAG, "Page selected: $position")
        }
    }

    private fun renderError(it: Failure?) {
        hideProgress()
        Snackbar.make(this.requireView(), it?.toString() ?: "Some error", Snackbar.LENGTH_LONG).show()
    }

    private fun renderView(it: CurrencyModel?) {
        hideProgress()
    }
}