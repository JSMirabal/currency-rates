package com.example.currency_rates.feature.main.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_rates.R
import com.example.currency_rates.core.extensions.inflate
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.feature.main.model.CurrencyModel
import com.example.currency_rates.feature.main.ui.holder.CurrencyAverageViewHolder
import com.example.currency_rates.feature.main.ui.holder.CurrencyHistoryViewHolder
import com.example.currency_rates.feature.main.ui.holder.CurrencyLatestViewHolder

class MainAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val HISTORY = 1
        private const val AVERAGE = 2
        private const val LATEST = 3
    }

    private val items by lazy {
        listOf(
            CurrencyModel(emptyList()).apply { type = HISTORY },
            CurrencyModel(emptyList()).apply { type = AVERAGE },
            CurrencyModel(emptyList()).apply { type = LATEST }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            HISTORY -> CurrencyHistoryViewHolder(parent.inflate(R.layout.currency_history_view_holder))
            AVERAGE -> CurrencyAverageViewHolder(parent.inflate(R.layout.currency_month_average_view_holder))
            LATEST -> CurrencyLatestViewHolder(parent.inflate(R.layout.currency_latest_exchange_view_holder))
            else -> CurrencyLatestViewHolder(parent.inflate(R.layout.currency_latest_exchange_view_holder))
        }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind()
    }
}