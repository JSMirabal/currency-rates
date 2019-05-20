package com.example.currency_rates.core.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager


class VerticalViewPager: ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return super.canScrollHorizontally(direction)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(flipXY(ev)).apply { flipXY(ev) }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(flipXY(ev)).apply { flipXY(ev) }
    }

    private fun flipXY(ev: MotionEvent?): MotionEvent? {
        ev ?: return ev
        val width = width.toFloat()
        val height = height.toFloat()
        val x = ev.y / height * width
        val y = ev.x / width * height
        ev.setLocation(x, y)
        return ev
    }

    private class VerticalPageTransformer : PageTransformer {
        override fun transformPage(view: View, position: Float) {
            val pageWidth = view.width
            val pageHeight = view.height
            if (position in -1F..1F) {
                view.alpha = 1F
                view.translationX = pageWidth * -position

                val yPosition = position * pageHeight
                view.translationY = yPosition
            } else {
                view.alpha = 0F
            }
        }
    }
}