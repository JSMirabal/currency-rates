package com.example.currency_rates.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.currency_rates.R

/**
 * Created by jsmirabal on 4/18/2019.
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment())
            .commit()
    }

    abstract fun fragment(): Fragment
}