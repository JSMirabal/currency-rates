package com.example.currency_rates.feature.main.ui.holder

import android.util.Log
import android.view.View
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.feature.main.model.CurrencyModel

class CurrencyAverageViewHolder(itemView: View) : BaseViewHolder<CurrencyModel.Average>(itemView) {

    override fun bind(data: CurrencyModel.Average) {
        Log.d("CurrencyAverage", "Binding...")
    }
}