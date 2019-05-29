package com.example.currency_rates.core.extensions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by jsmirabal on 4/18/2019.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.inflate(@LayoutRes layoutRes: Int, viewGroup: ViewGroup?): View =
    LayoutInflater.from(this).inflate(layoutRes, viewGroup, false)

fun Context.findRootView(): ViewGroup? = if (this is Activity) this.findViewById(android.R.id.content) else null

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}