package com.example.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class BaseAndroidTest {

    @Before
    abstract fun before()

    @After
    @Throws(IOException::class)
    abstract fun after()
}