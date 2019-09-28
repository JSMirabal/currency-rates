package com.example.currency_rates.feature.main.ui.holder

import android.util.Log
import android.view.View
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.feature.main.model.CurrencyModel

class CurrencyLatestViewHolder(itemView: View) : BaseViewHolder<CurrencyModel.Latest>(itemView) {

    override fun bind(data: CurrencyModel.Latest) {
        Log.d("CurrencyLatest", "Binding...")
    }
}