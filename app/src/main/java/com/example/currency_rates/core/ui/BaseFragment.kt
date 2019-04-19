package com.example.currency_rates.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.currency_rates.core.extensions.gone
import com.example.currency_rates.core.extensions.visible
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jsmirabal on 4/18/2019.
 */
abstract class BaseFragment: Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    internal fun showProgress() = activity?.spinner?.visible()

    internal fun hideProgress() = activity?.spinner?.gone()
}