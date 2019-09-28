package com.example.currency_rates.feature.main.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_rates.R
import com.example.currency_rates.core.extensions.inflate
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.feature.main.model.CurrencyModel
import com.example.currency_rates.feature.main.ui.MainViewType.*
import com.example.currency_rates.feature.main.ui.holder.CurrencyAverageViewHolder
import com.example.currency_rates.feature.main.ui.holder.CurrencyHistoryViewHolder
import com.example.currency_rates.feature.main.ui.holder.CurrencyLatestViewHolder

class MainAdapter : RecyclerView.Adapter<BaseViewHolder<out CurrencyModel>>() {

    private val items by lazy {
        mutableMapOf(
            HISTORY to CurrencyModel.History(),
            AVERAGE to CurrencyModel.Average(),
            LATEST to CurrencyModel.Latest()
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (MainViewType.getFrom(viewType)) {
            HISTORY -> CurrencyHistoryViewHolder(parent.inflate(R.layout.currency_history_view_holder))
            AVERAGE -> CurrencyAverageViewHolder(parent.inflate(R.layout.currency_month_average_view_holder))
            LATEST -> CurrencyLatestViewHolder(parent.inflate(R.layout.currency_latest_exchange_view_holder))
        }

    override fun getItemCount() = items.count()

    override fun getItemViewType(position: Int) = items.toList()[position].first.numericType

    override fun onBindViewHolder(holder: BaseViewHolder<out CurrencyModel>, position: Int) {
        holder.bind(items.toList()[position].second)
    }

    fun update(data: CurrencyModel) {
        when (data) {
            is CurrencyModel.History -> {
                items[HISTORY] = data
                notifyDataSetChanged()
            }
            is CurrencyModel.Average -> {
            }
            is CurrencyModel.Latest -> {
            }
        }
    }
}