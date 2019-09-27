package com.example.currency_rates.core.di

import com.example.currency_rates.core.di.viewmodel.ViewModelModule
import com.example.currency_rates.feature.main.ui.MainFragment
import com.example.data.di.DataComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jsmirabal on 5/1/2019.
 */
@Singleton
@Component(
    modules = [ApplicationModule::class, ViewModelModule::class],
    dependencies = [DataComponent::class]
)
interface ApplicationComponent {
    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun dataComponent(dataComponent: DataComponent): Builder

        fun appModule(applicationModule: ApplicationModule): Builder

        fun build(): ApplicationComponent
    }
}