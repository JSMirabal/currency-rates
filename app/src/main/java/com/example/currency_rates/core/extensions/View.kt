package com.example.currency_rates.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by jsmirabal on 4/18/2019.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.visible() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }