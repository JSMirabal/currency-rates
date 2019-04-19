package com.example.currency_rates.feature.main

import android.os.Bundle
import com.example.currency_rates.R
import com.example.currency_rates.core.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by jsmirabal on 4/18/2019.
 */
class MainFragment: BaseFragment() {

    override fun layoutId() = R.layout.fragment_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text.setOnClickListener { hideProgress() }
    }
}